package fr.eni.javaee.projetEncheres.dal;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur select(int no_utilisateur) throws BusinessException;
}
