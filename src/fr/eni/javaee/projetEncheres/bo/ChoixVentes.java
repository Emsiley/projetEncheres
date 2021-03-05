package fr.eni.javaee.projetEncheres.bo;

public abstract class ChoixVentes {
	public static final int ventesEnCours = 4;//Liste des Articles que j'ai mis en vente dont l'enchères est en cours
	public static final int ventesNonDebutees = 5;//Liste des Articles que j'ai mis en vente mais dont la date de début des enchères n'est pas atteinte
	public static final int ventesTerminees = 6;//Liste des Articles que j'ai mis en vente dont la date de fin d'énchère est atteinte
}
