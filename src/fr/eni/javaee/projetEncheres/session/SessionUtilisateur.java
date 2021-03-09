package fr.eni.javaee.projetEncheres.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtilisateur {

	public static void setSessionNo_utilisateur(HttpServletRequest request,Integer no_utilisteur) {
		HttpSession session = request.getSession();
		session.setAttribute("no_utilisateur", no_utilisteur);
	}
	
	public static void setSessionNo_utilisateur(HttpServletRequest request) {
		request.getSession().setAttribute("no_utilisateur", Integer.parseInt(request.getParameter("no_utilisateur")));
	}

	public static void closeSessionNo_utilisateur(HttpServletRequest request) {
		request.getSession().setAttribute("no_utilisateur", null);
	}

	
	//MOCK
	public static Integer getSessionNo_utilisateur(HttpServletRequest request) {
		return 1;//(Integer) request.getSession().getAttribute("no_utilisateur");
	}

	public static boolean isSameAttributSessionNo_utilisateur(HttpServletRequest request) {
	
		//String u = String.valueOf(getSessionNo_utilisateur(request));
		//Object ua = request.getAttribute("no_utilisateur");
		
		return getSessionNo_utilisateur(request)!= null 
				&&	(getSessionNo_utilisateur(request)==request.getAttribute("no_utilisateur"));
	}
	
	public static boolean isSameParametertSessionNo_utilisateur(HttpServletRequest request) {
		return getSessionNo_utilisateur(request)!= null 
				&&	(String.valueOf(getSessionNo_utilisateur(request)).equals(request.getParameter("no_utilisateur")));
	}
}
