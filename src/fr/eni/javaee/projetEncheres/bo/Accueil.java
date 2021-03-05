package fr.eni.javaee.projetEncheres.bo;

import java.util.ArrayList;

public class Accueil {
	
	Integer no_utilisateur = null;// null si utilisateur n'est pas connecté
	ArrayList<Article> listeArticles;
	
	//filtre utilisé si utilisateur est connecté ou non connecté
	String filtreNom = null;//null ou empty si pas de filtre pour la recherche
	Integer filtreNo_categorie = null;//null ou <0 si pas de catégorie choisie
	
	//filtre utilisé si utilisateur est connecté
	boolean radioBoutonsAchatVente = true;// si true : radio achat sinon c'est radioVente

	boolean achatsEnCours = true;	//Liste des Articles sur lequel on peut enchèrir
	boolean achatsMesEncheres = false; //Liste des Article sur lesquels j'ai posé une enchère
	boolean achatsMesAchats = false;//Liste des articles dont l'enchère est terminé et dont je suis le meilleur enchériseur en points
	
	boolean ventesEnCours = true;//Liste des Articles que j'ai mis en vente dont l'enchères est en cours 
	boolean ventesNonDebutees = false;//Liste des Articles que j'ai mis en vente mais dont la date de début des enchères n'est pas atteinte
	boolean ventesTerminees = false;//Liste des Articles que j'ai mis en vente dont la date de fin d'énchère est atteinte
	
	public boolean isConnected() {
		return this.no_utilisateur != null;
	}

	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public ArrayList<Article> getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(ArrayList<Article> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public Integer getFiltreNo_categorie() {
		return filtreNo_categorie;
	}

	public void setFiltreNo_categorie(Integer filtreNo_categorie) {
		this.filtreNo_categorie = filtreNo_categorie;
	}

	public boolean isRadioBoutonsAchatVente() {
		return radioBoutonsAchatVente;
	}

	public void setRadioBoutonsAchatVente(boolean radioBoutonsAchatVente) {
		this.radioBoutonsAchatVente = radioBoutonsAchatVente;
	}

	public boolean isAchatsEnCours() {
		return achatsEnCours;
	}

	public void setAchatsEnCours(boolean achatsEnCours) {
		this.achatsEnCours = achatsEnCours;
	}

	public boolean isAchatsMesEncheres() {
		return achatsMesEncheres;
	}

	public void setAchatsMesEncheres(boolean achatsMesEncheres) {
		this.achatsMesEncheres = achatsMesEncheres;
	}

	public boolean isAchatsMesAchats() {
		return achatsMesAchats;
	}

	public void setAchatsMesAchats(boolean achatsMesAchats) {
		this.achatsMesAchats = achatsMesAchats;
	}

	public boolean isVentesEnCours() {
		return ventesEnCours;
	}

	public void setVentesEnCours(boolean ventesEnCours) {
		this.ventesEnCours = ventesEnCours;
	}

	public boolean isVentesNonDebutees() {
		return ventesNonDebutees;
	}

	public void setVentesNonDebutees(boolean ventesNonDebutees) {
		this.ventesNonDebutees = ventesNonDebutees;
	}

	public boolean isVentesTerminees() {
		return ventesTerminees;
	}

	public void setVentesTerminees(boolean ventesTerminees) {
		this.ventesTerminees = ventesTerminees;
	}
	
	
}
