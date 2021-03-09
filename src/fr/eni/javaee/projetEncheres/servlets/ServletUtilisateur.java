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
		Object o = request.getAttribute("modification");
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
		request.setAttribute("mot_de_passe",util.getMot_de_passe());
		
		request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Je cherche à savoir si on a cliqué sur le bouton Modifier ou Enregister 
		//Enregistrer
		String e = request.getParameter("Enregistrer");
		request.setAttribute("modification",null);
		//Bouton Modifier
		if (request.getParameter("Enregistrer")!=null && request.getParameter("Enregistrer").equals("0")) 
		{
			//on rentre en modification dans l'écran du profil 
			request.setAttribute("modification","1");
		}
		
		//Bouton Enregistrer
		if (request.getParameter("Enregistrer")!=null && request.getParameter("Enregistrer").equals("1")) 
		{
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			
			//no_utilisateur, String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			//String ville,String mot_de_passe,int credit,boolean administrateur
			boolean administrateur = (request.getParameter("administrateur")!=null 
					&& request.getParameter("administrateur").equals("1"));
			
			Object cr = request.getParameter("credit");
			String crs = request.getParameter("credit");
			Object nu = request.getParameter("no_utilisateur");
			String nus = request.getParameter("no_utilisateur");			
			int credit = Integer.parseInt(cr!=null?cr.toString():"0");

			int no_utilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
			
			try {
				utilisateurManager.ModifierUtilisateur(
						no_utilisateur, 
						request.getParameter("pseudo"),
						request.getParameter("nom"),
						request.getParameter("prenom"),
						request.getParameter("email"),
						request.getParameter("telephone"),
						request.getParameter("rue"),
						request.getParameter("code_postal"),
						request.getParameter("ville"),
						request.getParameter("mot_de_passe"),
						credit,
						administrateur);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
