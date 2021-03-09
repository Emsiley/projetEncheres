package fr.eni.javaee.projetEncheres.dal;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur select(int no_utilisateur) throws BusinessException;
	public Utilisateur insert (String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException;
	public Utilisateur update (int no_utilisateur, String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException;
}
