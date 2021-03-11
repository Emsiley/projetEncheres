<%@page import="fr.eni.javaee.projetEncheres.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.ChoixAchats"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.ChoixVentes"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.Article"%>

<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accueil</title>
	</head>
	<body>
	
		<header>
			<h1>
				<a href="<%= request.getContextPath() %>">
					ENI-Enchères
				</a>
			</h1>
			
			<nav>
				<ul class="menu">
			       <li>
			           	<a href="<%=request.getContextPath()%>/Accueil">
			           		Enchères	
			           	</a>		           
			       </li>
			       <li>
			           	<a href="<%=request.getContextPath()%>/vendre">
			          		Vendre un article
			           	</a>
			       </li>
			       <li>
			           	<a href="<%= request.getContextPath() %>/Utilisateur?no_utilisateur=1">
			           		Mon profil
			           	</a>		     
			       </li>
			       <li>
			           	<a href="#">
				           Déconnexion
			           	</a>		     
			       </li>
			   	</ul>  
		   	</nav>
	    </header>
	   
	    <h2>
		    Liste des enchères
	    </h2>
	    
	    <section class="content">
	    
		    <h3>
			    Filtre de recherche
		    </h3>
			    
			<div>
				<%
					List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
					if( listeCodesErreur != null ) {
				%>
					<ul style="text-align: center;">
					<% for( int codeErreur:listeCodesErreur ) { %>
						<li class="erreur" style="color: red">
							ERROR : <%= LecteurMessage.getMessageErreur( codeErreur )%>
						</li>
					<% } %>
					</ul>
				<% } %>
		
		
				<form action="<%= request.getContextPath() %>/Accueil" method="post">
					
					<input type="hidden" name="no_utilisateur"     value="1"/>
					<input type="hidden" name="filtreNo_categorie" value="<%= request.getParameter("filtreNo_categorie") != null ? request.getParameter("filtreNo_categorie") : 1 %>"/>
						
					<div class="saisie">
						<input type="text" id="filtreNom" name="filtreNom" placeholder="Entrez le nom ou une partie du nom de l'article" value="<%= request.getParameter("filtreNom") != null ? request.getParameter("filtreNom") : "" %>"/>
					</div>
						
					<div class="search_filters">
						<ul>
							<li>
								<input type="radio" id="choixAchats<%=ChoixAchats.achatsEnCours%>" name="choixAchats" value="<%=ChoixAchats.achatsEnCours%>" 
								<%=(String.valueOf(ChoixAchats.achatsEnCours)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
								<label for="choixAchats<%=ChoixAchats.achatsEnCours%>">Enchères ouvertes</label>
							</li>
							<li>
								<input type="radio" id="choixAchats<%=ChoixAchats.achatsMesEncheres%>" name="choixAchats" value="<%=ChoixAchats.achatsMesEncheres%>" 
								<%=(String.valueOf(ChoixAchats.achatsMesEncheres)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
								<label for="choixAchats<%=ChoixAchats.achatsMesEncheres%>">Mes enchères</label>
							</li>
							<li>
								<input type="radio" id="choixAchats<%=ChoixAchats.achatsMesAchats%>" name="choixAchats" value="<%=ChoixAchats.achatsMesAchats%>" 
								<%=(String.valueOf(ChoixAchats.achatsMesAchats)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
								<label for="choixAchats<%=ChoixAchats.achatsMesAchats%>">Mes enchères remportées</label>
							</li>
						</ul>	
						<ul>
							<li>
								<input type="radio" id="choixVentes<%=ChoixVentes.ventesEnCours%>" name="choixVentes" value="<%=ChoixVentes.ventesEnCours%>" 
								<%=(String.valueOf(ChoixVentes.ventesEnCours)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
								<label for="choixVentes<%=ChoixVentes.ventesEnCours%>">Mes ventes en cours</label>
							</li>
							<li>
								<input type="radio" id="choixVentes<%=ChoixVentes.ventesNonDebutees%>" name="choixVentes" value="<%=ChoixVentes.ventesNonDebutees%>" 
								<%=(String.valueOf(ChoixVentes.ventesNonDebutees)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
								<label for="choixVentes<%=ChoixVentes.ventesNonDebutees%>">Ventes non débutées</label>
							</li>
							<li>
								<input type="radio" id="choixVentes<%=ChoixVentes.ventesTerminees%>" name="choixVentes" value="<%=ChoixVentes.ventesTerminees%>" 
								<%=(String.valueOf(ChoixVentes.ventesTerminees)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
								<label for="choixVentes<%=ChoixVentes.ventesTerminees%>">Ventes terminées</label>
							</li>
						</ul>	
					</div>		
													
					<div>
						<input type="submit" value="Rechercher"/>
						<a href="<%=request.getContextPath()%>" class="button">
							Annuler
						</a>
						<a href="<%=request.getContextPath()%>/vendre" class="button">
							Vendre un article
						</a>
					</div>
				</form>
		
			</div>	
			
		    <h3>
			    Résultats de recherche
		    </h3>		
		
			<%
				List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");									
				if( listeArticles != null && listeArticles.size() > 0 ) {
			%>				
				<section class="articles">			
					<% for( Article art : listeArticles ) { %>
						
						<article>
							<div>
								<img src="<%=request.getContextPath()%>/img/pc.jpg" alt="pcgamer" />				
							</div>
							
							<div>
								<p>
									<strong>
										<%= art.getNom_article() %>
									</strong>
									<br>
									Prix : <%= art.getPrix_vente() == 0 ? art.getPrix_initial() : art.getPrix_vente() %> points
								</p>
									
								<p>
									Fin de l'enchère : <%= art.getDate_fin_encheres() == null ? "N/A" : art.getDate_fin_encheres() %>
								</p>
								
								<p>								
									<a href="<%= request.getContextPath() %>/Utilisateur?no_utilisateur=<%= art.getNo_utilisateur() %>">
										Vendeur : #<%= art.getNo_utilisateur( )%>
									</a>
								</p>
							</div>
						</article>
					<% } %>
				</section>
				
			<% } else { %>		
		
				<div class="no-result">
					Aucun article trouvé :(
				</div>
		
			<% } %>
			
	    </section>
		
	</body>
</html>

