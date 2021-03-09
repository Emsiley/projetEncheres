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
	
	public Utilisateur CreeUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.validerString(pseudo,businessException,CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO);
		this.validerString(nom,businessException,CodesResultatBLL.REGLE_UTILISATEUR_NOM);
		this.validerString(prenom,businessException,CodesResultatBLL.REGLE_UTILISATEUR_PRENOM);
		this.validerString(email,businessException,CodesResultatBLL.REGLE_UTILISATEUR_EMAIL);
		
		this.validerString(rue,businessException,CodesResultatBLL.REGLE_UTILISATEUR_RUE);
		this.validerString(code_postal,businessException,CodesResultatBLL.REGLE_UTILISATEUR_CODEPOSTAL);
		this.validerString(ville,businessException,CodesResultatBLL.REGLE_UTILISATEUR_VILLE);
		this.validerString(mot_de_passe,businessException,CodesResultatBLL.REGLE_UTILISATEUR_MOTDEPASSE);

		Utilisateur utl=null;
		//TODO : regles metier utilisateur en fonction de no_utilisateur
		utl=this.utilisateurDAO.insert(pseudo, nom, prenom, email, telephone, rue,  code_postal,
				 ville, mot_de_passe, credit, administrateur);
		
		return utl;
	}
	
	public Utilisateur ModifierUtilisateur(int no_utilisateur, String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.validerInt(no_utilisateur, businessException, CodesResultatBLL.REGLE_UTILISATEUR_NO_UTILISATEUR);
		this.validerString(pseudo,businessException,CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO);
		this.validerString(nom,businessException,CodesResultatBLL.REGLE_UTILISATEUR_NOM);
		this.validerString(prenom,businessException,CodesResultatBLL.REGLE_UTILISATEUR_PRENOM);
		this.validerString(email,businessException,CodesResultatBLL.REGLE_UTILISATEUR_EMAIL);
		
		this.validerString(rue,businessException,CodesResultatBLL.REGLE_UTILISATEUR_RUE);
		this.validerString(code_postal,businessException,CodesResultatBLL.REGLE_UTILISATEUR_CODEPOSTAL);
		this.validerString(ville,businessException,CodesResultatBLL.REGLE_UTILISATEUR_VILLE);
		this.validerString(mot_de_passe,businessException,CodesResultatBLL.REGLE_UTILISATEUR_MOTDEPASSE);

		Utilisateur utl=null;
		//TODO : regles metier utilisateur en fonction de no_utilisateur
		utl=this.utilisateurDAO.update(no_utilisateur,pseudo, nom, prenom, email, telephone, rue,  code_postal,
				 ville, mot_de_passe, credit, administrateur);
		
		return utl;
	}	
	
	private void validerString(String str, BusinessException businessException, int erreurCode) {
		if(str==null || str.isEmpty())
		{
			businessException.ajouterErreur(erreurCode);
		}
		
	}
	private void validerInt(int i, BusinessException businessException, int erreurCode) {
		if(i<1)
		{
			businessException.ajouterErreur(erreurCode);
		}
		
	}	
}
