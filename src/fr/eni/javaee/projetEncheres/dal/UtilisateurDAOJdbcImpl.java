package fr.eni.javaee.projetEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.projetEncheres.BusinessException;
import fr.eni.javaee.projetEncheres.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private static final String SELECT_UTILISATEUR = "SELECT no_utilisateur,pseudo,nom,prenom,email,"
			+ " telephone,rue,code_postal,ville,"
			+ " mot_de_passe,credit,administrateur"
			+ " FROM [BDD_ENCHERES].[dbo].[UTILISATEURS]"
			+ " WHERE no_utilisateur = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO [dbo].[UTILISATEURS] (pseudo,nom,prenom,email,telephone,rue,"
			+ " code_postal,ville,mot_de_passe,credit,administrateur VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_UTILISATEUR = "UPDATE [dbo].[UTILISATEURS] SET"
			+ " [pseudo] = ?, [nom] = ?, [prenom] = ?, [email] = ?, [telephone] = ?, [rue] = ?, [code_postal] = ?,"
			+ " [ville] = ?, [mot_de_passe] = ?, [credit] = ? ,[administrateur] = ? WHERE no_utilisateur = ? ";
	
	@Override
	public Utilisateur select(int no_utilisateur) throws BusinessException {
		
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR);
			pstmt.setInt(1, no_utilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				
				utilisateur = utilsateurBuilder(rs);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
	private Utilisateur utilsateurBuilder(ResultSet rs) throws SQLException {
		Utilisateur utl;
		utl=new Utilisateur();
		utl.setNo_utilisateur(rs.getInt("no_utilisateur"));
		utl.setPseudo(rs.getString("pseudo"));
		utl.setNom(rs.getString("nom"));
		utl.setPrenom(rs.getString("prenom"));
		utl.setEmail(rs.getString("email"));
		utl.setTelephone(rs.getString("telephone"));
		utl.setRue(rs.getString("rue"));
		utl.setCode_postal(rs.getString("code_postal"));
		utl.setVille(rs.getString("ville"));
		utl.setMot_de_passe(rs.getString("mot_de_passe"));
		utl.setCredit(rs.getInt("credit"));
		utl.setAdministrateur(rs.getBoolean("administrateur"));
		
		return utl;
	}

	@Override
	public Utilisateur insert (String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException {
		Utilisateur util = new Utilisateur();

			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, pseudo);
				pstmt.setString(2, nom);
				pstmt.setString(3, prenom);
				pstmt.setString(4, email);
				pstmt.setString(5, telephone);
				pstmt.setString(6, rue);
				pstmt.setString(7, code_postal);
				pstmt.setString(8, ville);
				pstmt.setString(9, mot_de_passe);
				pstmt.setInt(10, credit);
				pstmt.setBoolean(11, administrateur);
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					util.setNo_utilisateur(rs.getInt(1));
					util.setPseudo(pseudo);
					util.setNom(nom);
					util.setPrenom(prenom);
					util.setEmail(email);
					util.setTelephone(telephone);
					util.setRue(rue);
					util.setCode_postal(code_postal);
					util.setVille(ville);
					util.setMot_de_passe(mot_de_passe);
					util.setCredit(credit);
					util.setAdministrateur(administrateur);
				}
				rs.close();
				pstmt.close();
				

			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
				throw businessException;
			}
		return util;
	}
	
	@Override
	public Utilisateur update (int no_utilisateur, String pseudo,String nom,String prenom,String email,String telephone,String rue, String code_postal,
			String ville,String mot_de_passe,int credit,boolean administrateur) throws BusinessException {
		Utilisateur util = new Utilisateur();

			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
				pstmt.setString(1, pseudo);
				pstmt.setString(2, nom);
				pstmt.setString(3, prenom);
				pstmt.setString(4, email);
				pstmt.setString(5, telephone);
				pstmt.setString(6, rue);
				pstmt.setString(7, code_postal);
				pstmt.setString(8, ville);
				pstmt.setString(9, mot_de_passe);
				pstmt.setInt(10, credit);
				pstmt.setBoolean(11, administrateur);
				pstmt.setInt(12,no_utilisateur);
				pstmt.executeUpdate();

				util.setNo_utilisateur(no_utilisateur);
				util.setPseudo(pseudo);
				util.setNom(nom);
				util.setPrenom(prenom);
				util.setEmail(email);
				util.setTelephone(telephone);
				util.setRue(rue);
				util.setCode_postal(code_postal);
				util.setVille(ville);
				util.setMot_de_passe(mot_de_passe);
				util.setCredit(credit);
				util.setAdministrateur(administrateur);

				pstmt.close();
				

			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
				throw businessException;
			}
		return util;
	}
}
