package fr.eni.javaee.projetEncheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bll.ArticlesManager;
import fr.eni.javaee.projetEncheres.bll.UtilisateurManager;
import fr.eni.javaee.projetEncheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletUtilisateur
 */
@WebServlet("/Utilisateur")
public class ServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		//String no_utilisateur = request.getParameter("no_utilisateur");
		Utilisateur util = null;
		String nu = request.getParameter("no_utilisateur");
		int no_utilisateur = Integer.parseInt(nu);

		//TODO no utilisateur à définir
		try {
			util = utilisateurManager.RechercheUtilisateur(no_utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO set des attributes pour l'ecrans profil	
		request.setAttribute("no_utilisateur",util.getNo_utilisateur());
		request.setAttribute("pseudo",util.getPseudo());
		request.setAttribute("nom",util.getNom());
		request.setAttribute("prenom",util.getPrenom());
		request.setAttribute("email",util.getEmail());
		request.setAttribute("telephone",util.getTelephone());
		request.setAttribute("rue",util.getRue());
		request.setAttribute("code_postal",util.getCode_postal());
		request.setAttribute("ville",util.getVille());
		request.setAttribute("credit",util.getCredit());
		
		request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
