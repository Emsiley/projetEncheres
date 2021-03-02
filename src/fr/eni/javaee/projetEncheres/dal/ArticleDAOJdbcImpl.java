package fr.eni.javaee.projetEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Article;
import fr.eni.javaee.projetEncheres.dal.CodesResultatDAL;
import fr.eni.javaee.projetEncheres.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String INSERT_ARTICLE="insert into ARTICLES_VENDUS "
			+ "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) "
			+ "values(?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT="insert into RETRAITS "
			+ "([no_article],[rue],[code_postal],[ville]) "
			+ "values(?,?,?,?)";
	//	private static final String INSERT_ALIMENT="insert into aliments(nom, id_repas) values(?,?)";
//	private static final String SELECT_ALL=" SELECT " + 
//											"	r.id as id_repas," + 
//											"	r.date_repas," + 
//											"	r.heure_repas," + 
//											"	a.id as id_aliment," + 
//											"	a.nom" + 
//											" FROM" + 
//											"	REPAS r" + 
//											"	INNER JOIN ALIMENTS a ON r.id=a.id_repas" +
//											"	ORDER BY r.date_repas desc, r.heure_repas desc";
//	private static final String SELECT_UNE_DATE=" SELECT " + 
//											"	r.id as id_repas," + 
//											"	r.date_repas," + 
//											"	r.heure_repas," + 
//											"	a.id as id_aliment," + 
//											"	a.nom" + 
//											" FROM" + 
//											"	REPAS r" + 
//											"	INNER JOIN ALIMENTS a ON r.id=a.id_repas" +
//											" WHERE r.date_repas=?"+
//											"	ORDER BY r.date_repas desc, r.heure_repas desc";
	
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
				//nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie
				pstmt.setString(1, article.getNom_article());
				pstmt.setString(2,article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDate_debut_encheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDate_fin_encheres()));
				pstmt.setInt(5, article.getPrix_initial());
				//no_utilisateur	pseudo	nom		prenom
				//1					test	test	test
				pstmt.setInt(6, article.getNo_utilisateur());
				//no_categorie	libelle
				//1				divers
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
}
