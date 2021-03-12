package fr.eni.javaee.projetEncheres.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Article {
    int no_article=0;                    //INTEGER IDENTITY(1,1) NOT NULL,
    String nom_article;                   //VARCHAR(30) NOT NULL,
    String description;                   //VARCHAR(300) NOT NULL,
	LocalDate date_debut_encheres;           //DATE NOT NULL,
	LocalDate date_fin_encheres;             //DATE NOT NULL,
    int prix_initial=0;                  //INTEGER,
    int prix_vente=0;                    //INTEGER,
    int no_utilisateur=0;                //INTEGER NOT NULL,
    int no_categorie=0;                  //INTEGER NOT NULL

    Retrait retrait;
    
	public int getNo_article() {
		return no_article;
	}
	public void setNo_article(int no_article) {
		this.no_article = no_article;
		if (this.retrait != null) retrait.setNo_article(no_article);
	}
	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(LocalDate date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public LocalDate getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(LocalDate date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}
	public int getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}
	public int getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	public Retrait getRetrait() {
		return retrait;
	}
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	
    
}
