package fr.eni.javaee.projetEncheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bll.ArticlesManager;
import fr.eni.javaee.projetEncheres.bo.Accueil;
import fr.eni.javaee.projetEncheres.bo.Article;
import fr.eni.javaee.projetEncheres.bo.ChoixAchats;
import fr.eni.javaee.projetEncheres.bo.ChoixVentes;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// fait un forward sur l'écran accueil.jsp
		request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		//recupération des parametre de l'ecran accueil.jsp
		Integer no_utilisateur = null;// null si utilisateur n'est pas connecté
		ArrayList<Article> listeArticles = null;
		
		//filtre utilisé si utilisateur est connecté ou non connecté
		String filtreNom = null;//null ou empty si pas de filtre pour la recherche
		Integer filtreNo_categorie = null;//null ou <0 si pas de catégorie choisie
		
		//filtre utilisé si utilisateur est connecté
		boolean radioBoutonsAchatVente = true;// si true : radio achat sinon c'est radioVente
		
		boolean achatsEnCours = false;	//Liste des Articles sur lequel on peut enchèrir
		boolean achatsMesEncheres = false; //Liste des Article sur lesquels j'ai posé une enchère
		boolean achatsMesAchats = false;//Liste des articles dont l'enchère est terminé et dont je suis le meilleur enchériseur en points
		
		boolean ventesEnCours = false;//Liste des Articles que j'ai mis en vente dont l'enchères est en cours 
		boolean ventesNonDebutees = false;//Liste des Articles que j'ai mis en vente mais dont la date de début des enchères n'est pas atteinte
		boolean ventesTerminees = false;//Liste des Articles que j'ai mis en vente dont la date de fin d'énchère est atteinte
	    
		try {    
			if (request.getParameter("no_utilisateur")!=null && !request.getParameter("no_utilisateur").isEmpty()) 
			{
				no_utilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_NO_UTILISATEUR_ERREUR);
		}
		
		filtreNom = request.getParameter("filtreNom");
		
		try {
			String cat = (String)request.getParameter("no_categorie");
			filtreNo_categorie = Integer.parseInt(cat);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_NO_CATEGORIES_ERREUR);
		}
		
		Integer choixAchats = null;
		try {    
			if (request.getParameter("choixAchats")!=null && !request.getParameter("choixAchats").isEmpty()) 
			{
				choixAchats = Integer.parseInt(request.getParameter("choixAchats"));
				switch(choixAchats)
		        {
		            case ChoixAchats.achatsEnCours :
		            	achatsEnCours = true;
		            break;
		            case ChoixAchats.achatsMesAchats :
		            	achatsMesAchats = true;
		            break;
		            case ChoixAchats.achatsMesEncheres :
		            	achatsMesEncheres = true;
		            break;
		        }
				radioBoutonsAchatVente = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_FILTRE_ACCUEIL_ERREUR);
		}

		Integer choixVentes = null;
		try {    
			if (request.getParameter("choixVentes")!=null && !request.getParameter("choixVentes").isEmpty()) 
			{
				choixVentes = Integer.parseInt(request.getParameter("choixVentes"));
				switch(choixVentes)
		        {
		            case ChoixVentes.ventesEnCours :
		            	ventesEnCours = true;
		            break;
		            case ChoixVentes.ventesNonDebutees :
		            	ventesNonDebutees = true;
		            break;
		            case ChoixVentes.ventesTerminees :
		            	ventesTerminees = true;
		            break;
		        }
				radioBoutonsAchatVente = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_FILTRE_ACCUEIL_ERREUR);
		}		
		ArrayList<Article> las = null;
		if(listeCodesErreur.size()>0)
		{
			//Je renvoie les codes d'erreurs
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			rd.forward(request, response);
		}
		else
		{
			
			//Je récupère la liste des articles
			ArticlesManager articleManager = new ArticlesManager();
			try {
				las = articleManager.ListerArticles(no_utilisateur,filtreNom ,filtreNo_categorie,
				radioBoutonsAchatVente ,achatsEnCours,achatsMesEncheres,achatsMesAchats,
				ventesEnCours,ventesNonDebutees,ventesTerminees);
				
				request.setAttribute("listeArticles",las);
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			} catch (BusinessException e) {
				//Sinon je retourne à  la page d'accueil pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			}
		}
		
	
	}

}
