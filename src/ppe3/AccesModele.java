package ppe3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import fr.sio.TestJDBC;

public class AccesModele {

	private List<Visiteur> visiteurs = new ArrayList<Visiteur>() ;
	private List<RapportVisite> rapportsVisite = new ArrayList<RapportVisite>() ;
	private List<RapportVisite> rapportsVisiteSelec = new ArrayList<RapportVisite>() ;
	private List<String> nomVisiteurs = new ArrayList<String>() ;
	private List<String> prenomVisiteurs = new ArrayList<String>() ;
	
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement ps = null;
	
	private String matRap = "";
	private String sMatriculeDelegC = "";
	
	public AccesModele(){
		super();
	}
	
	public void remplirVisiteurs(String matDeg) {
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String url = "jdbc:mysql://localhost/GsbCRSlam";
		    conn = DriverManager.getConnection(url, "root", "mysql");
		    selectVisiteurs(matDeg);
	    }
		    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	 }
	
	public void remplirRapports(String matDeg, int year, int month) {
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String url = "jdbc:mysql://localhost/GsbCRSlam";
		    conn = DriverManager.getConnection(url, "root", "mysql");
		    this.rapportsVisite.clear();
		    selectRapportsVisite(matDeg, year, month);
	    }
		    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	 }
	
	public void remplirRapport(String matDeg) {
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String url = "jdbc:mysql://localhost/GsbCRSlam";
		    conn = DriverManager.getConnection(url, "root", "mysql");
		    selectUniqueRapportVisite(matDeg);
	    }
		    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	 }
	 
	 public void selectVisiteurs(String matDeg) {
		  System.out.println("Remplissage Visiteurs :");
		  String query = "SELECT VISITEUR.VIS_MATRICULE, VIS_NOM, VIS_PRENOM, VIS_ADRESSE, VIS_CP FROM VISITEUR "
		  		+ "INNER JOIN TRAVAILLER on VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE "
		  		+ "WHERE TRA_ROLE = 'Visiteur' "
		  		+ "AND TRAVAILLER.REG_CODE IN (SELECT REGION.REG_CODE FROM REGION "
		  		+ "								INNER JOIN TRAVAILLER "
		  		+ "								ON TRAVAILLER.REG_CODE = REGION.REG_CODE "
		  		+ "								WHERE TRAVAILLER.VIS_MATRICULE=? "
		  		+ "								AND TRAVAILLER.TRA_ROLE!='Visiteur') ";
		  try {
			  PreparedStatement st = conn.prepareStatement(query);
			  st.setString(1,matDeg);
			  ResultSet rs = st.executeQuery();
			  while (rs.next()) {
				  String numero = rs.getString("VIS_MATRICULE");
				  String nom = rs.getString("VIS_NOM") ;
				  String prenom = rs.getString("VIS_PRENOM") ;
				  String adresse = rs.getString("VIS_ADRESSE");
				  String numCp = rs.getString("VIS_CP");
				  this.visiteurs.add(new Visiteur(numero,nom,prenom,adresse,numCp)) ;
			  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public List<Visiteur> getVisiteurs() {
		System.out.println("AccesModele::getVisiteurs()") ;
		return this.visiteurs;
	}
	 
	 public void selectRapportsVisite(String matDeg, int year, int month) {
		  System.out.println("Remplissages Rapports :");
		  String query = "SELECT PRATICIEN.PRA_NOM, PRATICIEN.PRA_VILLE, "
		  		+ "RAPPORT_VISITE.RAP_DATE, RAPPORT_VISITE.RAP_DATE_REDAC, "
		  		+ "RAPPORT_VISITE.RAP_BILAN, RAPPORT_VISITE.RAP_LU, "
		  		+ "RAPPORT_VISITE.VIS_MATRICULE, RAPPORT_VISITE.RAP_NUM,"
		  		+ "VISITEUR.VIS_NOM "
		  		+ "FROM RAPPORT_VISITE, PRATICIEN, VISITEUR "
		  		+ "WHERE RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM "
		  		+ "AND RAPPORT_VISITE.VIS_MATRICULE=VISITEUR.VIS_MATRICULE "
		  		+ "AND RAPPORT_VISITE.VIS_MATRICULE=? "
		  		+ "AND YEAR(RAP_DATE)=? "
		  		+ "AND MONTH(RAP_DATE)=? ";
		  try {
			  PreparedStatement ps = conn.prepareStatement(query);
			  ps.setString(1, matDeg);
			  ps.setInt(2, year);
			  ps.setInt(3, month);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  String matVis = rs.getString("RAPPORT_VISITE.VIS_MATRICULE");
				  String nomPra = rs.getString("PRATICIEN.PRA_NOM");
				  String villePra = rs.getString("PRATICIEN.PRA_VILLE") ;
				  Date dateVisite = rs.getDate("RAPPORT_VISITE.RAP_DATE") ;
				  Date dateRedac = rs.getDate("RAPPORT_VISITE.RAP_DATE_REDAC") ;
				  String bilan = rs.getString("RAPPORT_VISITE.RAP_BILAN");
				  String lu = rs.getString("RAPPORT_VISITE.RAP_LU");
				  String rapNum = rs.getString("RAPPORT_VISITE.RAP_NUM");
				  String nomVis = rs.getString("VISITEUR.VIS_NOM");
				  GregorianCalendar dateVisite2 = new GregorianCalendar();
				  GregorianCalendar dateRedac2 = new GregorianCalendar();
				  dateVisite2.setTime(dateVisite);
				  dateRedac2.setTime(dateRedac);
				  this.rapportsVisite.add(new RapportVisite(rapNum,nomPra,nomVis,matVis,dateVisite2,dateRedac2,villePra,bilan,lu)) ;
				  //System.out.println(visiteurs.get(i));
				  //i ++;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public void selectUniqueRapportVisite(String matRap) {
		  System.out.println("Remplissages Rapports :");
		  String query = "SELECT PRATICIEN.PRA_NOM, PRATICIEN.PRA_VILLE, "
		  		+ "RAPPORT_VISITE.RAP_DATE, RAPPORT_VISITE.RAP_DATE_REDAC, "
		  		+ "RAPPORT_VISITE.RAP_BILAN, RAPPORT_VISITE.RAP_LU, "
		  		+ "RAPPORT_VISITE.VIS_MATRICULE, RAPPORT_VISITE.RAP_NUM,"
		  		+ "VISITEUR.VIS_NOM "
		  		+ "FROM RAPPORT_VISITE, PRATICIEN, VISITEUR "
		  		+ "WHERE RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM "
		  		+ "AND RAPPORT_VISITE.VIS_MATRICULE=VISITEUR.VIS_MATRICULE "
		  		+ "AND RAPPORT_VISITE.RAP_NUM=? ";
		  try {
			  PreparedStatement ps = conn.prepareStatement(query);
			  ps.setString(1, matRap);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  String matVis = rs.getString("RAPPORT_VISITE.VIS_MATRICULE");
				  String nomPra = rs.getString("PRATICIEN.PRA_NOM");
				  String villePra = rs.getString("PRATICIEN.PRA_VILLE") ;
				  Date dateVisite = rs.getDate("RAPPORT_VISITE.RAP_DATE") ;
				  Date dateRedac = rs.getDate("RAPPORT_VISITE.RAP_DATE_REDAC") ;
				  String bilan = rs.getString("RAPPORT_VISITE.RAP_BILAN");
				  String lu = rs.getString("RAPPORT_VISITE.RAP_LU");
				  String rapNum = rs.getString("RAPPORT_VISITE.RAP_NUM");
				  String nomVis = rs.getString("VISITEUR.VIS_NOM");
				  GregorianCalendar dateVisite2 = new GregorianCalendar();
				  GregorianCalendar dateRedac2 = new GregorianCalendar();
				  dateVisite2.setTime(dateVisite);
				  dateRedac2.setTime(dateRedac);
				  this.rapportsVisiteSelec.add(new RapportVisite(rapNum,nomPra,nomVis,matVis,dateVisite2,dateRedac2,villePra,bilan,lu)) ;
				  //System.out.println(visiteurs.get(i));
				  //i ++;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public void setReadRapportsVisite(String numRapport) {
		  String query = "UPDATE RAPPORT_VISITE "
		  		+ "SET RAP_LU ='Oui' "
		  		+ "WHERE RAP_LU ='Non' "
		  		+ "AND RAP_NUM= ?";
		  try {
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  String url = "jdbc:mysql://localhost/GsbCRSlam";
			  conn = DriverManager.getConnection(url, "root", "mysql");
			  PreparedStatement st = conn.prepareStatement(query);
			  st.setString(1, numRapport);
			  int rs = st.executeUpdate();
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
		  catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		  catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		  catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public void setDontReadRapportsVisite() {
		  String query = "UPDATE RAPPORT_VISITE "
		  		+ "SET RAP_LU ='Non' "
		  		+ "WHERE RAP_LU ='Oui' " ;
		  try {
			  Statement st = conn.createStatement();
			  int rs = st.executeUpdate(query);
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}

	public void selectBilanRapportsVisite() {
		  //System.out.println("Remplissage Visiteurs :");
		  String query = "SELECT VISITEUR.VIS_NOM, RAPPORT_VISITE.RAP_BILAN "
		  		+ "FROM RAPPORT_VISITE JOIN VISITEUR "
		  		+ "ON RAPPORT_VISITE.VIS_MATRICULE=VISITEUR.VIS_MATRICULE "
		  		+ "WHERE VISITEUR.VIS_NOM=?";
		  try {
			  PreparedStatement st = conn.prepareStatement(query);
			  st.setString(1, "Villechalane");
			  rs = st.executeQuery(query);
			  while (rs.next()) {
				  String nom = rs.getString("VISITEUR.VIS_NOM");
				  String bilan = rs.getString("RAPPORT_VISITE.RAP_BILAN");
			  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}

	public List<RapportVisite> getRapportsVisite() {
		return rapportsVisite;
	}
	
	public String getMatRap() {
		return matRap;
	}

	public void setMatRap(String matRap) {
		this.matRap = matRap;
	}

	/** Obtenir le matricule du délégué connecté
	 *
	 * @return Matricule du délégué connecté
	 */
	public String getDelegueConnecter(){
		return this.sMatriculeDelegC;
	}
	/** Définir un matricule du délégué connecté
	 *
	 * @param sMatriculeDelegC Matricule du délégué
	 */
	public void setDelegueConnecter(String sMatriculeDelegC){
		this.sMatriculeDelegC = sMatriculeDelegC;
	}

	/** Se connecter en tant que délégué régional
	 *
	 * @param login Identifiant de l'utilisateur
	 * @param mdp Mot de passe de l'utilisateur
	 * @return Vrai si l'identifiant et le mot de passe correspondent
	 * @throws SQLException Peut générer une exception sql
	 */
	public boolean seConnecter(String login, String mdp) throws SQLException {
		boolean bToAccess = false;
		String query = "SELECT VISITEUR.VIS_MATRICULE ,VIS_MDP "
				+ "FROM VISITEUR "
				+ "INNER JOIN TRAVAILLER ON VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE "
				+ "WHERE VISITEUR.VIS_MATRICULE=? AND VISITEUR.VIS_MDP=? AND TRAVAILLER.TRA_ROLE != 'Visiteur'";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/GsbCRSlam";
			conn = DriverManager.getConnection(url, "root", "mysql");
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, login);
			st.setString(2, mdp);
			rs = st.executeQuery();
			while (rs.next()) {
				if(login.equals(rs.getString("VIS_MATRICULE"))) {
					if(mdp.equals(rs.getString("VIS_MDP"))) {
						bToAccess = true;
						this.setDelegueConnecter(rs.getString("VIS_MATRICULE"));
						this.remplirVisiteurs(rs.getString("VIS_MATRICULE"));
						
					}
				}
			}
		}
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		
		return bToAccess;
	}
	
	/** Réinitialiser tous les attributs de la classe
	 *
	 */
	public void resetData(){
		this.visiteurs = new ArrayList<Visiteur>() ;
		this.rapportsVisite = new ArrayList<RapportVisite>() ;
		this.sMatriculeDelegC = null;
		this.matRap = null;
	}
}