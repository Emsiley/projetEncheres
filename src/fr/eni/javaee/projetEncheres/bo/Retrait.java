package fr.eni.javaee.projetEncheres.bo;

public class Retrait {
	int no_article;       //INTEGER NOT NULL,
    String rue;              //VARCHAR(30) NOT NULL,
    String code_postal;      //VARCHAR(15) NOT NULL,
    String ville;            //VARCHAR(30) NOT NULL
	public int getNo_article() {
		return no_article;
	}
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

}
