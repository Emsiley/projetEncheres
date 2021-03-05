package fr.eni.javaee.projetEncheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée Ã  l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de la lecture des Articles
	 */
	public static final int LECTURE_ARTICLES_ECHEC=10002;
	/**
	 * Echec de la lecture de la liste d'Articles affiché sur l'ecran d'accueil
	 */
	public static final int LECTURE_ACCUEIL_ECHEC=10003;
}