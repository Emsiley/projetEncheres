package fr.eni.javaee.projetEncheres.bo;

public abstract class ChoixVentes {
	public static final int ventesEnCours = 4;//Liste des Articles que j'ai mis en vente dont l'ench�res est en cours
	public static final int ventesNonDebutees = 5;//Liste des Articles que j'ai mis en vente mais dont la date de d�but des ench�res n'est pas atteinte
	public static final int ventesTerminees = 6;//Liste des Articles que j'ai mis en vente dont la date de fin d'�nch�re est atteinte
}
