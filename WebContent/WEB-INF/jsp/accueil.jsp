<%@page import="fr.eni.javaee.projetEncheres.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.ChoixAchats"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.ChoixVentes"%>
<%@page import="fr.eni.javaee.projetEncheres.bo.Article"%>

<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Accueil</title>
</head>
<body>
	<div class="contenu">
		<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur, les choix de recherche n'ont pas aboutis :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>


		<form action="<%=request.getContextPath()%>/Accueil" method="post">
			<div class="saisie" style="visibility:hidden;">
				<input type="number" name="no_utilisateur" value="1"/>
			</div>
			<div class="saisie">
				<label for="filtreNom">Le nom de l'article contient : </label>
				<input type="text" id="filtreNom" name="filtreNom" value="<%=request.getParameter("filtreNom")!=null?request.getParameter("filtreNom"):""%>"/>
			</div>
			<div class="saisie"  style="visibility:hidden;">
				<input type="number" name="filtreNo_categorie" value="1"/>
				<!--  value="<%=request.getParameter("filtreNo_categorie")!=null?request.getParameter("filtreNo_categorie"):""%>"/>  -->
			</div>			
			<ul>
				<li>
					<input type="radio" id="choixAchats<%=ChoixAchats.achatsEnCours%>" name="choixAchats" value="<%=ChoixAchats.achatsEnCours%>" 
					<%=(String.valueOf(ChoixAchats.achatsEnCours)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
					<label for="choixAchats<%=ChoixAchats.achatsEnCours%>">enchères ouvertes</label>
				</li>
				<li>
					<input type="radio" id="choixAchats<%=ChoixAchats.achatsMesEncheres%>" name="choixAchats" value="<%=ChoixAchats.achatsMesEncheres%>" 
					<%=(String.valueOf(ChoixAchats.achatsMesEncheres)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
					<label for="choixAchats<%=ChoixAchats.achatsMesEncheres%>">mes enchères</label>
				</li>
				<li>
					<input type="radio" id="choixAchats<%=ChoixAchats.achatsMesAchats%>" name="choixAchats" value="<%=ChoixAchats.achatsMesAchats%>" 
					<%=(String.valueOf(ChoixAchats.achatsMesAchats)).equals(request.getParameter("choixAchats"))?"checked":""%>/>
					<label for="choixAchats<%=ChoixAchats.achatsMesAchats%>">mes enchères remportées</label>
				</li>
			</ul>	
			<ul>
				<li>
					<input type="radio" id="choixVentes<%=ChoixVentes.ventesEnCours%>" name="choixVentes" value="<%=ChoixVentes.ventesEnCours%>" 
					<%=(String.valueOf(ChoixVentes.ventesEnCours)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
					<label for="choixVentes<%=ChoixVentes.ventesEnCours%>">mes ventes en cours</label>
				</li>
				<li>
					<input type="radio" id="choixVentes<%=ChoixVentes.ventesNonDebutees%>" name="choixVentes" value="<%=ChoixVentes.ventesNonDebutees%>" 
					<%=(String.valueOf(ChoixVentes.ventesNonDebutees)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
					<label for="choixVentes<%=ChoixVentes.ventesNonDebutees%>">ventes non débutées</label>
				</li>
				<li>
					<input type="radio" id="choixVentes<%=ChoixVentes.ventesTerminees%>" name="choixVentes" value="<%=ChoixVentes.ventesTerminees%>" 
					<%=(String.valueOf(ChoixVentes.ventesTerminees)).equals(request.getParameter("choixVentes"))?"checked":""%>/>
					<label for="choixVentes<%=ChoixVentes.ventesTerminees%>">ventes terminées</label>
				</li>
			</ul>									
			<div>
				<input type="submit" value="Rechercher"/>
				<a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
			</div>
		</form>

	</div>
	
	
	<table align="center">
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prix</td>
				<td>Date fin</td>
				<td>Vendeur</td>
			</tr>
		</thead>
			<%
				List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");
				if(listeArticles!=null && listeArticles.size()>0)
				{
			%>
					<tbody>
						<%
						for(Article art : listeArticles)
						{
						%>
							<tr>
								<td><%=art.getNom_article()%></td>
								<td><%=art.getPrix_vente()==0?art.getPrix_initial():art.getPrix_vente()%></td>
								<td><%=art.getDate_fin_encheres()==null?"":art.getDate_fin_encheres()%></td>								
								<td><a href="<%=request.getContextPath()%>/Utilisateur?no_utilisateur=<%=art.getNo_utilisateur()%>"><%=art.getNo_utilisateur()%></a></td>
							</tr>
						<%
						}
						%>
					</tbody>
			<%
				}
				else
				{
			%>
				<p>Il n'y a aucun article à afficher<P>
			<%
				}
			%>
	</table>
	
	
	<div class="contenu">
		<a href="<%=request.getContextPath()%>/vendre"><input type="button" value="(en test) Vendre un article"/></a>
	</div>



</body>
</html>

