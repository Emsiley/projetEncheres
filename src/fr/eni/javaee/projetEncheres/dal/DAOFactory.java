package fr.eni.javaee.projetEncheres.dal;

public abstract class DAOFactory {
	
	public static ArticleDAO getArticleDAO()
	{
		return new ArticleDAOJdbcImpl();
	}
}
	