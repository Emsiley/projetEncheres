package fr.eni.javaee.projetEncheres.dal;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Article;

public interface ArticleDAO {
	
	public void insert(Article article) throws BusinessException;

}
