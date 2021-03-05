package fr.eni.javaee.projetEncheres.bll;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Utilisateur;
import fr.eni.javaee.projetEncheres.dal.DAOFactory;
import fr.eni.javaee.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	public UtilisateurManager(){
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	
	public Utilisateur RechercheUtilisateur(int no_utilisateur) throws BusinessException {
		Utilisateur utl=null;
		//TODO : regles metier utilisateur en fonction de no_utilisateur
		utl=this.utilisateurDAO.select(no_utilisateur);
		return utl;
	}
}
