package fr.eni.javaee.projetEncheres.dal;

import java.util.ArrayList;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Accueil;
import fr.eni.javaee.projetEncheres.bo.Article;

public interface ArticleDAO {
	
	public void insert(Article article) throws BusinessException;
	public ArrayList<Article> select(Accueil accueil) throws BusinessException;
}
