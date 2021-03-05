package fr.eni.javaee.projetEncheres.dal.mock;



import fr.eni.javaee.projetEncheres.BusinessException;

import fr.eni.javaee.projetEncheres.bo.Utilisateur;
import fr.eni.javaee.projetEncheres.dal.UtilisateurDAO;

public class UtilsateurDAOMock implements UtilisateurDAO {

	@Override
	public Utilisateur select(int no_utilisateur) throws BusinessException {
		Utilisateur utl = new Utilisateur();
		utl.setNo_utilisateur(no_utilisateur);
		utl.setPseudo("JeanBono");
		utl.setNom("Bon");
		utl.setPrenom("Jean");
		utl.setEmail("Jean.Bon@charcuterie.fr");
		utl.setTelephone("0601020304");
		utl.setRue("rue de la charcuterie");
		utl.setCode_postal("07000");
		utl.setVille("St jean pied de porc");
		return utl;
	}
}
