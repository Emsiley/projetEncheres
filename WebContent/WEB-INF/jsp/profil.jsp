<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.projetEncheres.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.projetEncheres.session.SessionUtilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
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
<!-- 		request.setAttribute("no_utilisateur",util.getNo_utilisateur());
		request.setAttribute("pseudo",util.getPseudo());
		request.setAttribute("nom",util.getNom());
		request.setAttribute("prenom",util.getPrenom());
		request.setAttribute("email",util.getEmail());
		request.setAttribute("telephone",util.getTelephone());
		request.setAttribute("rue",util.getRue());
		request.setAttribute("code_postal",util.getCode_postal());
		request.setAttribute("ville",util.getVille());
		request.setAttribute("credit",util.getCredit()); -->

		<form action="<%=request.getContextPath()%>/Utilisateur" method="post">
			<div class="saisie" style="visibility:hidden;">
				<input type="number" name="no_utilisateur" value="<%=request.getAttribute("no_utilisateur")%>"/>
			</div>
			<div class="saisie">
				<label for="pseudo">pseudo : </label>
				<input type="text" id="pseudo" name="pseudo" value="<%=request.getAttribute("pseudo")!=null?request.getAttribute("pseudo"):""%>"/>
			</div>
			<div class="saisie">
				<label for="nom">nom : </label>
				<input type="text" id="nom" name="nom" value="<%=request.getAttribute("nom")!=null?request.getAttribute("nom"):""%>"/>
			</div>
			<div class="saisie">
				<label for="prenom">prenom : </label>
				<input type="text" id="prenom" name="prenom" value="<%=request.getAttribute("prenom")!=null?request.getAttribute("prenom"):""%>"/>
			</div>
			<div class="saisie">
				<label for="email">email : </label>
				<input type="text" id="email" name="email" value="<%=request.getAttribute("email")!=null?request.getAttribute("email"):""%>"/>
			</div>
			<div class="saisie">
				<label for="telephone">telephone : </label>
				<input type="text" id="telephone" name="telephone" value="<%=request.getAttribute("telephone")!=null?request.getAttribute("telephone"):""%>"/>
			</div>
			<div class="saisie">
				<label for="rue">rue : </label>
				<input type="text" id="rue" name="rue" value="<%=request.getAttribute("rue")!=null?request.getAttribute("rue"):""%>"/>
			</div>
			<div class="saisie">
				<label for="code_postal">code_postal : </label>
				<input type="text" id="code_postal" name="code_postal" value="<%=request.getAttribute("code_postal")!=null?request.getAttribute("code_postal"):""%>"/>
			</div>
			<div class="saisie">
				<label for="ville">ville : </label>
				<input type="text" id="ville" name="ville" value="<%=request.getAttribute("ville")!=null?request.getAttribute("ville"):""%>"/>
			</div>
			<div class="saisie">
				<label for="credit">credit : </label>
				<input type="text" id="credit" name="credit" value="<%=request.getAttribute("credit")!=null?request.getAttribute("credit"):""%>"/>
			</div>
			
			
			<div>
				<%
				if(SessionUtilisateur.getSessionNo_utilisateur()!= null 
					&&	(String.valueOf(SessionUtilisateur.getSessionNo_utilisateur())).equals(request.getAttribute("no_utilisateur")))
				{
				%>
				<input type="submit" value="Modifier"/>
				<%	
				}
				%>
			
				<a href="<%=request.getContextPath()%>"><input type="button" value="retour"/></a>
			</div>
		</form>


</body>
</html>

