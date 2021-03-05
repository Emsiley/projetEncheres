package fr.eni.javaee.projetEncheres.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Accueil;
import fr.eni.javaee.projetEncheres.bo.Article;
import fr.eni.javaee.projetEncheres.bo.Retrait;
import fr.eni.javaee.projetEncheres.dal.ArticleDAO;
import fr.eni.javaee.projetEncheres.dal.ArticleDAOJdbcImpl;
import fr.eni.javaee.projetEncheres.dal.CodesResultatDAL;
import fr.eni.javaee.projetEncheres.dal.DAOFactory;
import fr.eni.javaee.projetEncheres.bll.CodesResultatBLL;

/*
Vendre un article	
En tant qu’utilisateur, je peux vendre un article sur la plateforme ENI-Enchères. 
Pour cela je donne les informations sur l’article vendu : 
	nom, 
	description 
	et catégorie 
	j’indique un prix de départ ( en points ), 
	une date 
	et une heure d’ouverture de l’enchère, 
	une date 
	et une heure de fin d’enchères 
	et les modalités du retrait :  adresse (par défaut celle du vendeur).
*/
//TODO : ajouter les modalités du retrait :  adresse (par défaut celle du vendeur). cf BO la class retrait
public class ArticlesManager {
	private ArticleDAO articleDao;
	
	// TODO : DAOFactory ?
	public ArticlesManager() {
		this.articleDao = DAOFactory.getArticleDAO();//new ArticleDAOJdbcImpl();
	}
	

	
	public Article ajouterArticle(String nom_article,String description,LocalDate date_debut_encheres,LocalTime heure_debut_encheres,
			LocalDate date_fin_encheres,LocalTime heure_fin_encheres,int prix_initial,int no_categorie,int no_utilisateur,
			String rue,String code_postal,String ville) throws BusinessException
	{
		//TODO vérification métier
		BusinessException businessException = new BusinessException();
		this.validerString(nom_article,businessException,CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
		this.validerString(description,businessException,CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		this.validerDateHeure(date_debut_encheres, heure_debut_encheres, businessException,CodesResultatBLL.REGLE_ARTICLE_DATE_ET_HEURE_DEBUT_ERREUR);
		this.validerDateHeure(date_fin_encheres, heure_fin_encheres, businessException,CodesResultatBLL.REGLE_ARTICLE_DATE_ET_HEURE_FIN_ERREUR);
		this.validerInt(prix_initial, businessException,CodesResultatBLL.REGLE_ARTICLE_PRIX_INITIAL_ERREUR);
		this.validerInt(no_categorie, businessException, CodesResultatBLL.REGLE_ARTICLE_NO_CATEGORIES_ERREUR);
		this.validerString(rue,businessException,CodesResultatBLL.REGLE_RUE_ERREUR);
		this.validerString(code_postal,businessException,CodesResultatBLL.REGLE_CODE_POSTAL_ERREUR);
		this.validerString(ville,businessException,CodesResultatBLL.REGLE_VILLE_ERREUR);
		this.validerInt(no_utilisateur, businessException, CodesResultatBLL.REGLE_ARTICLE_NO_UTILISATEUR_ERREUR);
		//TODO: insert article via dao
		Article article = new Article();
		article.setNom_article(nom_article);
		article.setDescription(description);
		article.setDate_debut_encheres(date_debut_encheres);
		//article.setHeure_debut_encheres(heure_debut_encheres);
		article.setDate_fin_encheres(date_fin_encheres);
		//article.setHeure_fin_encheres(heure_fin_encheres);
		article.setPrix_initial(prix_initial);
		article.setNo_categorie(no_categorie);
		//TODO : utilisateur test : no_utilisateur  = 1
		article.setNo_utilisateur(no_utilisateur);
		
		Retrait r = new Retrait();
		r.setRue(rue);
		r.setCode_postal(code_postal);
		r.setVille(ville);
		
		article.setRetrait(r);
		
		articleDao.insert(article);

		return article;
		
	}
	
	public ArrayList<Article> ListerArticles(
			Integer no_utilisateur,String filtreNom ,Integer filtreNo_categorie,
			boolean radioBoutonsAchatVente,boolean achatsEnCours,boolean achatsMesEncheres,boolean achatsMesAchats,
			boolean ventesEnCours,boolean ventesNonDebutees,boolean ventesTerminees) throws BusinessException
	{
		Accueil accueil = new Accueil();
		accueil.setNo_utilisateur(no_utilisateur);
		accueil.setFiltreNom(filtreNom);
		accueil.setFiltreNo_categorie(filtreNo_categorie);
		accueil.setRadioBoutonsAchatVente(radioBoutonsAchatVente);
		accueil.setAchatsEnCours(achatsEnCours);
		accueil.setAchatsMesEncheres(achatsMesEncheres);
		accueil.setAchatsMesAchats(achatsMesAchats);
		accueil.setVentesEnCours(ventesEnCours);
		accueil.setVentesNonDebutees(ventesNonDebutees);
		accueil.setVentesTerminees(ventesTerminees);
	
		return articleDao.select(accueil);
	}
	
	private void validerDateHeure(LocalDate date, LocalTime heure, BusinessException businessException, int erreurCode) {
		if(date==null || 
			date.isAfter(LocalDate.now()) ||
			(date.isEqual(LocalDate.now()) && heure.isAfter(LocalTime.now())))
		{
			businessException.ajouterErreur(erreurCode);
		}
		
	}
	private void validerString(String str, BusinessException businessException, int erreurCode) {
		if(str==null || str.isEmpty())
		{
			businessException.ajouterErreur(erreurCode);
		}
		
	}
	private void validerInt(int i, BusinessException businessException, int erreurCode) {
		if(i<1)
		{
			businessException.ajouterErreur(erreurCode);
		}
		
	}	
}
