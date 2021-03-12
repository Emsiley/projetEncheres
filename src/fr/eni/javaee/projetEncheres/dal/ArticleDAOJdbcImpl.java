package fr.eni.javaee.projetEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Accueil;
import fr.eni.javaee.projetEncheres.bo.Article;


public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String INSERT_ARTICLE="insert into ARTICLES_VENDUS "
			+ "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) "
			+ "values(?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT="insert into RETRAITS "
			+ "([no_article],[rue],[code_postal],[ville]) "
			+ "values(?,?,?,?)";
	
	private static final String FILTRE_UTILISATEUR = "[no_utilisateur] = ? ";
	private static final String FILTRE_ARTICLE = " AND [nom_article] like '%'+?+'%' ";
	private static final String FILTRE_CATEGORIE = " AND [no_categorie] = ? ";
	private static final String TRIE_ARTICLES = " ORDER BY date_debut_encheres desc, [ARTICLES_VENDUS].[no_article] desc ";
	
	//filtre utilisé si utilisateur est connecté ou non connecté
	
	private static final String SELECT_ALL_ACHATS_ENCOURS="SELECT [no_article],[nom_article],[description],"
			+ "[date_debut_encheres],[date_fin_encheres],[prix_initial]"
			+ ",[prix_vente],[no_utilisateur],[no_categorie]"
			+ " FROM [BDD_ENCHERES].[dbo].[ARTICLES_VENDUS]"
			+ " WHERE [date_debut_encheres] <= GETDATE() "
			+ " AND ([date_fin_encheres] is not null AND [date_fin_encheres] > GETDATE()) ";
	
	//filtre utilisé si utilisateur est connecté
	
	private static final String SELECT_ALL_ACHATS="SELECT [ARTICLES_VENDUS].[no_article],[nom_article],[description]"
			+ "[date_debut_encheres],[date_fin_encheres],[prix_initial]"
			+ ",[prix_vente],[ARTICLES_VENDUS].[no_utilisateur],[no_categorie] "
			+ "  FROM [BDD_ENCHERES].[dbo].[ARTICLES_VENDUS] "
			+ "  INNER JOIN [ENCHERES] on [ENCHERES].[no_article] = [ARTICLES_VENDUS].[no_article] "
			+ "  WHERE [ENCHERES]." + FILTRE_UTILISATEUR;
	private static final String SELECT_ALL_ACHATS_MES_ENCHERES=SELECT_ALL_ACHATS;
	
	private static final String SELECT_ALL_ACHATS_MES_ACHATS=SELECT_ALL_ACHATS 
			+ " AND montant_enchere = [prix_vente] AND [date_fin_encheres] <= GETDATE() ";
	
	private static final String SELECT_ALL_VENTES = "SELECT [no_article],[nom_article],[description],"
			+ "[date_debut_encheres],[date_fin_encheres],[prix_initial]"
			+ ",[prix_vente],[no_utilisateur],[no_categorie]"
			+ " FROM [BDD_ENCHERES].[dbo].[ARTICLES_VENDUS]"
			+ " WHERE " + FILTRE_UTILISATEUR;
	private static final String SELECT_ALL_VENTES_ENCOURS = SELECT_ALL_VENTES 
			+ " AND [date_debut_encheres] <= GETDATE() AND ([date_fin_encheres] is not null AND [date_fin_encheres] > GETDATE()) ";
	private static final String SELECT_ALL_VENTES_NON_DEBUTEES	 = SELECT_ALL_VENTES 
			+ " AND [date_debut_encheres] > GETDATE() ";
	private static final String SELECT_ALL_VENTES_TERMINEES	 = SELECT_ALL_VENTES 
			+ " AND ([date_fin_encheres] is not null AND [date_fin_encheres] <= GETDATE()) ";

	
	@Override
	public void insert(Article article) throws BusinessException {
		if(article==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				//TODO : traiter catégorie et utilisateur
				cnx.setAutoCommit(false);
				
				//ARTICLES_VENDUS
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNom_article());
				pstmt.setString(2,article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDate_debut_encheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDate_fin_encheres()));
				pstmt.setInt(5, article.getPrix_initial());
				pstmt.setInt(6, article.getNo_utilisateur());
				pstmt.setInt(7, article.getNo_categorie());

				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					article.setNo_article(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				
				//RETRAIT
				pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, article.getNo_article());
				pstmt.setString(2, article.getRetrait().getRue());
				pstmt.setString(3, article.getRetrait().getCode_postal());
				pstmt.setString(4, article.getRetrait().getVille());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				rs.close();
				pstmt.close();
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}
	
	@Override
	public ArrayList<Article> select(Accueil accueil) throws BusinessException {
		ArrayList<Article> listArticle = new ArrayList<Article>();
		if(accueil==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ACCUEIL_ECHEC);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				String selectAll = "";
				
				//Liste des Articles sur lequel on peut enchèrir
				if (!accueil.isConnected() 
						|| (accueil.isConnected() && accueil.isAchatsEnCours() && accueil.isRadioBoutonsAchatVente())) {
					selectAll = SELECT_ALL_ACHATS_ENCOURS;
				}
				
				//Liste des Article sur lesquels j'ai posé une enchère
				if (accueil.isConnected() && accueil.isAchatsMesEncheres() && accueil.isRadioBoutonsAchatVente()) {
					selectAll = SELECT_ALL_ACHATS_MES_ENCHERES;
				}
				
				//Liste des articles dont l'enchère est terminé et dont je suis le meilleur enchériseur en points
				if (accueil.isConnected() && accueil.isAchatsMesAchats() && accueil.isRadioBoutonsAchatVente()) {
					selectAll = SELECT_ALL_ACHATS_MES_ACHATS;
				}
				//Liste des Articles que j'ai mis en vente dont l'enchères est en cours 
				if (accueil.isConnected() && accueil.isVentesEnCours() && !accueil.isRadioBoutonsAchatVente()) {
					selectAll = SELECT_ALL_VENTES_ENCOURS;
				}
				//Liste des Articles que j'ai mis en vente mais dont la date de début des enchères n'est pas atteinte
				if (accueil.isConnected() && accueil.isVentesNonDebutees() && !accueil.isRadioBoutonsAchatVente()) {
					selectAll = SELECT_ALL_VENTES_NON_DEBUTEES;
				}
				//Liste des Articles que j'ai mis en vente dont la date de fin d'énchère est atteinte
				if (accueil.isConnected() && accueil.isVentesTerminees() && !accueil.isRadioBoutonsAchatVente()) {
					selectAll = SELECT_ALL_VENTES_TERMINEES;
				}
				
				if (accueil.getFiltreNom()!=null && !accueil.getFiltreNom().isEmpty()) {
					selectAll = selectAll + FILTRE_ARTICLE;
				}
				if (accueil.getFiltreNo_categorie() != null && accueil.getFiltreNo_categorie() > 0) {
					selectAll = selectAll + FILTRE_CATEGORIE;
				}
				//Ajout du trie
				selectAll = selectAll +TRIE_ARTICLES ;
				//SELECT_ALL
				PreparedStatement pstmt = cnx.prepareStatement(selectAll);
				int i = 1;
				
				if (selectAll.contains(FILTRE_UTILISATEUR)) pstmt.setInt(i++, accueil.getNo_utilisateur());
				if (selectAll.contains(FILTRE_ARTICLE)) pstmt.setString(i++, accueil.getFiltreNom());
				if (selectAll.contains(FILTRE_CATEGORIE)) pstmt.setInt(i++, accueil.getFiltreNo_categorie());		
				
				ResultSet rs = pstmt.executeQuery();
				Article articleCourant=new Article();
				while(rs.next())
				{
					articleCourant = articleBuilder(rs);
					listArticle.add(articleCourant);
				}
				rs.close();
				pstmt.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}		
		
		
		return listArticle;
	}
	
	private Article articleBuilder(ResultSet rs) throws SQLException {
		Article articleCourant;
		articleCourant=new Article();
		articleCourant.setNo_article(rs.getInt("no_article"));
		articleCourant.setNom_article(rs.getString("nom_article"));
		articleCourant.setDescription(rs.getString("description"));
		articleCourant.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
		articleCourant.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
		articleCourant.setPrix_initial(rs.getInt("prix_initial"));
		articleCourant.setPrix_vente(rs.getInt("prix_vente"));
		articleCourant.setNo_utilisateur(rs.getInt("no_utilisateur"));
		articleCourant.setNo_categorie(rs.getInt("no_categorie"));
		
		return articleCourant;
	}

	
}
