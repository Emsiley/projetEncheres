package fr.eni.javaee.projetEncheres.dal.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Accueil;
import fr.eni.javaee.projetEncheres.bo.Article;
import fr.eni.javaee.projetEncheres.dal.ArticleDAO;

public class ArticleDAOMock implements ArticleDAO {

	@Override
	public void insert(Article article) throws BusinessException {
		// C'est un mock ont fait semblant :  feindre, simuler, faire croire ...		
	}

	@Override
	public ArrayList<Article> select(Accueil accueil) throws BusinessException {
		// C'est un mock ont fait semblant :  feindre, simuler, faire croire ....
		ArrayList<Article> liste = new ArrayList<Article>();
		{
			Article a = new Article();
			a.setNo_article(1);
			a.setNom_article("Test nom article");
			a.setDescription("Test description article");
			a.setDate_debut_encheres(LocalDate.of(2021, 2, 21));
			a.setDate_fin_encheres(LocalDate.of(2021, 3, 21));
			a.setPrix_initial(110);
			a.setNo_categorie(1);
			a.setNo_utilisateur(1);	
			liste.add(a);
		}
		{
			Article a = new Article();
			a.setNo_article(2);
			a.setNom_article("Test nom article 2");
			a.setDescription("Test description article 2");
			a.setDate_debut_encheres(LocalDate.of(2021, 2, 22));
			a.setDate_fin_encheres(LocalDate.of(2021, 3, 22));
			a.setPrix_initial(210);
			a.setNo_categorie(1);
			a.setNo_utilisateur(2);	
	
			liste.add(a);
		}	
		{
			Article a = new Article();
			a.setNo_article(3);
			a.setNom_article("Test nom article 3");
			a.setDescription("Test description article 3");
			a.setDate_debut_encheres(LocalDate.of(2021, 2, 23));
			a.setDate_fin_encheres(LocalDate.of(2021, 3, 23));
			a.setPrix_initial(310);
			a.setNo_categorie(1);
			a.setNo_utilisateur(3);	
	
			liste.add(a);
		}
		return liste;
	}

}
