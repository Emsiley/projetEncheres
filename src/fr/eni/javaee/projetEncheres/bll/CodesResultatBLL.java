package fr.eni.javaee.projetEncheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	public static final int REGLE_ARTICLE_NOM_ERREUR=20000;		
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR=20001;
	
	public static final int REGLE_ARTICLE_NO_UTILISATEUR_ERREUR=20002;
	public static final int REGLE_ARTICLE_DATE_ET_HEURE_DEBUT_ERREUR=20003;
	public static final int REGLE_ARTICLE_DATE_ET_HEURE_FIN_ERREUR=20004;
	
	public static final int REGLE_ARTICLE_PRIX_INITIAL_ERREUR=20005;
	public static final int REGLE_ARTICLE_NO_CATEGORIES_ERREUR=20006;
	public static final int REGLE_RUE_ERREUR=20007;
	public static final int REGLE_CODE_POSTAL_ERREUR=20008;
	public static final int REGLE_VILLE_ERREUR=20009;
	
	public static final int REGLE_UTILISATEUR_PSEUDO = 20050;
	public static final int REGLE_UTILISATEUR_NOM = 20051;
	public static final int REGLE_UTILISATEUR_PRENOM = 20052;
	public static final int REGLE_UTILISATEUR_EMAIL = 20053;
	public static final int REGLE_UTILISATEUR_RUE = 20054;
	public static final int REGLE_UTILISATEUR_CODEPOSTAL = 20055;
	public static final int REGLE_UTILISATEUR_VILLE = 20056;
	public static final int REGLE_UTILISATEUR_MOTDEPASSE = 20057;
	public static final int REGLE_UTILISATEUR_NO_UTILISATEUR = 20058;
/*
    String pseudo;          //VARCHAR(30) NOT NULL,
    String nom;              //VARCHAR(30) NOT NULL,
    String prenom;           //VARCHAR(30) NOT NULL,
    String email;            //VARCHAR(50) NOT NULL,

    String rue;              //VARCHAR(30) NOT NULL,
    String code_postal;      //VARCHAR(10) NOT NULL,
    String ville;            //VARCHAR(50) NOT NULL,
    String mot_de_passe;     //VARCHAR(30) NOT NULL, */	
	
}
