package fr.eni.javaee.projetEncheres.dal;

import fr.eni.javaee.projetEncheres.dal.mock.ArticleDAOMock;
import fr.eni.javaee.projetEncheres.dal.mock.UtilsateurDAOMock;

public abstract class DAOFactory {
	
	public static ArticleDAO getArticleDAO()
	{
		//return new ArticleDAOMock();
		return new ArticleDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		//return new UtilsateurDAOMock();
		return new UtilisateurDAOJdbcImpl();
	}
}
	