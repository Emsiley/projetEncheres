<%@page import="fr.eni.javaee.projetEncheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Vendre</title>
</head>
<body>
	<div class="contenu">
		<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur, l'article à vendre n'a pas pu être ajouté :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
		<%
		//TODO à remplacer
		request.setAttribute("no_utilisateur", "1"); //à prendre dans la session 
		//TODO (si ils sont non renseignés) faire set Attribut rue, code_postal et Ville pris dans UTILISATEUR 
		request.setAttribute("no_categorie", new Integer(1)); //à saisir via un popup de la table categories
		%>

		<form action="<%=request.getContextPath()%>/vendre" method="post">
			<div class="saisie" style="visibility:hidden;">
				<input type="number" name="no_utilisateur" value="1"/>
			</div>
			<div class="saisie">
				<label for="nom_article">Nom de L'article : </label>
				<input type="text" id="nom_article" name="nom_article" type="text" value="<%=request.getParameter("nom_article")!=null?request.getParameter("nom_article"):""%>"/>
			</div>
			<div class="saisie">
				<label for="description">Description : </label>
				<textarea rows="5" cols="30" id="description" name="description" ><%=request.getParameter("description")!=null?request.getParameter("description"):""%></textarea>
			</div>
			<div class="saisie">
				<label for="date_debut_encheres">date debut des enchères : </label>
				<input type="date" name="date_debut_encheres" value="<%=request.getParameter("date_debut_encheres")%>"/>
			</div>
			<div class="saisie">
				<label for="date_fin_encheres">date fin des enchères : </label>
				<input type="date" name="date_fin_encheres" value="<%=request.getParameter("date_fin_encheres")%>"/>
			</div>
			<div class="saisie"  style="visibility:hidden;">
				<input type="number" name="no_categorie" value="1"/>
			</div>			
			<div class="saisie">
				<label for="prix_initial">Prix initial : </label>
				<input type="number" name="prix_initial" value="<%=request.getParameter("prix_initial")!=null?request.getParameter("prix_initial"):"1"%>"/>
			</div>
			<div class="saisie">
				<label for="rue">Rue : </label>
				<input type="text" name="rue" value="<%=request.getParameter("rue")!=null?request.getParameter("rue"):""%>"/>
			</div>
			<div class="saisie">
				<label for="code_postal">Code postal : </label>
				<input type="text" name="code_postal" value="<%=request.getParameter("code_postal")!=null?request.getParameter("code_postal"):""%>"/>
			</div>
			<div class="saisie">
				<label for="ville">Ville : </label>
				<input type="text" name="ville" value="<%=request.getParameter("ville")!=null?request.getParameter("ville"):""%>"/>
			</div>
						
			<div>
				<input type="submit" value="Valider"/>
				<a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
			</div>
		</form>

	</div>



</body>
</html>