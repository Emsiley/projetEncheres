package fr.eni.javaee.projetEncheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.projetEncheres.bll.ArticlesManager;
import fr.eni.javaee.projetEncheres.bo.Article;
import fr.eni.javaee.projetEncheres.servlets.CodesResultatServlets;
import fr.eni.javaee.projetEncheres.BusinessException;


/**
 * Servlet implementation class ServletVendre
 */
@WebServlet("/vendre")
public class ServletVendre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO faire un forward sur l'écran vendre.jsp
		request.getRequestDispatcher("/WEB-INF/jsp/vente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur=new ArrayList<>();
		
	    //int no_article;                    //INTEGER IDENTITY(1,1) NOT NULL,
	    String nom_article=null;                   //VARCHAR(30) NOT NULL,
	    String description=null;                   //VARCHAR(300) NOT NULL,
		LocalDate date_debut_encheres=null;           //DATE NOT NULL,
		LocalTime heure_debut_encheres=null;
		LocalDate date_fin_encheres=null;             //DATE NOT NULL,
		LocalTime heure_fin_encheres=null;
	    int prix_initial=0;                  //INTEGER,
	    int no_utilisateur=1;                //INTEGER NOT NULL,
	    int no_categorie=1;                  //INTEGER NOT NULL
	    String rue=null;              //VARCHAR(30) NOT NULL,
	    String code_postal=null;      //VARCHAR(15) NOT NULL,
	    String ville=null;            //VARCHAR(30) NOT NULL		
	    
	    
	    nom_article = request.getParameter("nom_article");
		if(nom_article==null || nom_article.trim().isEmpty())
		{
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_NOM_ERREUR);
		}
		description = request.getParameter("description");
		if(description==null || description.trim().isEmpty())
		{
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DESCRIPTION_ERREUR);
		}	    
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date_debut_encheres = LocalDate.parse(request.getParameter("date_debut_encheres"),dtf);
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");
			heure_debut_encheres= LocalTime.parse(request.getParameter("heure_debut_encheres"),dtf1);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DATE_ET_HEURE_DEBUT_ERREUR);
		}
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date_fin_encheres = LocalDate.parse(request.getParameter("date_fin_encheres"),dtf);
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");
			heure_fin_encheres= LocalTime.parse(request.getParameter("heure_fin_encheres"),dtf1);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DATE_ET_HEURE_FIN_ERREUR);
		}
		try {
			String prx = request.getParameter("prix_initial");
			prix_initial = Integer.parseInt(prx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_PRIX_INITIAL_ERREUR);
		}
		try {
			String utl = (String)request.getParameter("no_utilisateur");
			no_utilisateur = Integer.parseInt(utl);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_NO_UTILISATEUR_ERREUR);
		}
		try {
			String cat = (String)request.getParameter("no_categorie");
			no_categorie = Integer.parseInt(cat);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_NO_CATEGORIES_ERREUR);
		}		
	    rue = request.getParameter("rue");
		if(rue==null || rue.trim().isEmpty())
		{
			listeCodesErreur.add(CodesResultatServlets.FORMAT_RUE_ERREUR);
		}
		code_postal = request.getParameter("code_postal");
		if(code_postal==null || code_postal.trim().isEmpty())
		{
			listeCodesErreur.add(CodesResultatServlets.FORMAT_CODE_POSTAL_ERREUR);
		}	
		ville = request.getParameter("ville");
		if(ville==null || ville.trim().isEmpty())
		{
			listeCodesErreur.add(CodesResultatServlets.FORMAT_VILLE_ERREUR);
		}		
		
		
		Article art = null;
		if(listeCodesErreur.size()>0)
		{
			//Je renvoie les codes d'erreurs
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vente.jsp");
			rd.forward(request, response);
		}
		else
		{
			
			//J'ajoute le repas
			ArticlesManager articleManager = new ArticlesManager();
			try {
				art = articleManager.ajouterArticle(
						nom_article,description,
						date_debut_encheres,heure_debut_encheres,
						date_fin_encheres,heure_fin_encheres,
						prix_initial,
						no_categorie,no_utilisateur,
						rue,code_postal,ville);
				
				
				//Si tout se passe bien, je vais vers la page d'accueil :
				//request.getRequestDispatcher("").forward(request, response);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				//Sinon je retourne à  la page de saisie de la vente pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/jsp/vente.jsp").forward(request, response);
			}
		}
		
	}

}
