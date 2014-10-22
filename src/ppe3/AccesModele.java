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
	private List<String> nomVisiteurs = new ArrayList<String>() ;
	private List<String> prenomVisiteurs = new ArrayList<String>() ;
	
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement ps = null;
	
	public AccesModele(){
		JDBCheck();
	}
	
	public void JDBCheck() {
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String url = "jdbc:mysql://localhost/GsbCRSlam";
		    conn = DriverManager.getConnection(url, "root", "mysql");
		    faireTests();
	    }
		    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	 }
	 private void faireTests() {
		 selectVisiteurs();
		 selectRapportsVisite();
	 }

	 private void selectAttributsVisiteurs() {
		  System.out.println("Vérification Nom, Prenom des Visiteurs :");
		  String query = "SELECT * FROM VISITEUR";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
			  int i = 0;
			  while (rs.next()) {
				  String nom = rs.getString("VIS_NOM") ;
				  String prenom = rs.getString("VIS_PRENOM") ;
				  //System.out.println(nom + "   " + prenom);
				  this.nomVisiteurs.add(nom);
				  this.prenomVisiteurs.add(prenom);
				  System.out.print(nomVisiteurs.get(i)+" ");
				  System.out.println(prenomVisiteurs.get(i));
				  i ++ ;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	 }
	 
	 private void selectAttributsPraticiens() {
		  System.out.println("Vérification Nom, Prenom des Visiteurs :");
		  String query = "SELECT * FROM PRATICIEN";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
			  int i = 0;
			  while (rs.next()) {
				  String nom = rs.getString("PRA_NOM") ;
				  String prenom = rs.getString("PRA_PRENOM") ;
				  //System.out.println(nom + "   " + prenom);
				  this.nomVisiteurs.add(nom);
				  this.prenomVisiteurs.add(prenom);
				  System.out.print(nomVisiteurs.get(i)+" ");
				  System.out.println(prenomVisiteurs.get(i));
				  i ++ ;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	 }
	 
	 public void selectVisiteurs() {
		  System.out.println("Remplissage Visiteurs :");
		  String query = "SELECT VIS_MATRICULE, VIS_NOM, VIS_PRENOM, VIS_ADRESSE FROM VISITEUR";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
			  int i = 0;
			  while (rs.next()) {
				  String numero = rs.getString("VIS_MATRICULE");
				  String nom = rs.getString("VIS_NOM") ;
				  String prenom = rs.getString("VIS_PRENOM") ;
				  String adresse = rs.getString("VIS_ADRESSE");
				  this.visiteurs.add(new Visiteur(numero,nom,prenom,adresse)) ;
				  //System.out.println(visiteurs.get(i));
				  //i ++;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public List<Visiteur> getVisiteurs() {
		System.out.println("AccesModele::getVisiteurs()") ;
		return this.visiteurs;
	}
	 
	 public void selectRapportsVisite() {
		  System.out.println("Remplissages Rapports :");
		  String query = "SELECT PRATICIEN.PRA_NOM, PRATICIEN.PRA_VILLE, "
		  		+ "RAPPORT_VISITE.RAP_DATE, RAPPORT_VISITE.RAP_DATE_REDAC, "
		  		+ "RAPPORT_VISITE.RAP_BILAN, RAPPORT_VISITE.RAP_LU, "
		  		+ "RAPPORT_VISITE.VIS_MATRICULE "
		  		+ "FROM RAPPORT_VISITE, PRATICIEN "
		  		+ "WHERE RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM ;";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
			  //int i = 0;
			  while (rs.next()) {
				  String matVis = rs.getString("RAPPORT_VISITE.VIS_MATRICULE");
				  String nomPra = rs.getString("PRATICIEN.PRA_NOM");
				  String villePra = rs.getString("PRATICIEN.PRA_VILLE") ;
				  Date dateVisite = rs.getDate("RAPPORT_VISITE.RAP_DATE") ;
				  Date dateRedac = rs.getDate("RAPPORT_VISITE.RAP_DATE_REDAC") ;
				  String bilan = rs.getString("RAPPORT_VISITE.RAP_BILAN");
				  String lu = rs.getString("RAPPORT_VISITE.RAP_LU");
				  GregorianCalendar dateVisite2 = new GregorianCalendar();
				  GregorianCalendar dateRedac2 = new GregorianCalendar();
				  dateVisite2.setTime(dateVisite);
				  dateRedac2.setTime(dateRedac);
				  this.rapportsVisite.add(new RapportVisite(nomPra,matVis,dateVisite2,dateRedac2,villePra,bilan,lu)) ;
				  //System.out.println(visiteurs.get(i));
				  //i ++;
				  }
		  }
		  catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
	 
	 public void insertReadRapportsVisite() {
		  String query = "SELECT PRATICIEN.PRA_NOM, PRATICIEN.PRA_VILLE,"
		  		+ " RAPPORT_VISITE.RAP_DATE, RAP_DATE_REDAC, RAP_LU "
		  		+ "FROM RAPPORT_VISITE join PRATICIEN "
		  		+ "ON RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM "
		  		+ "AND RAPPORT_VISITE.PRA_NUM";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
			  int i = 0;
			  while (rs.next()) {
				  String numero = rs.getString("PRATICIEN.PRA_NOM");
				  String nom = rs.getString("PRATICIEN.PRA_VILLE") ;
				  Date prenom = rs.getDate("RAPPORT_VISITE.RAP_DATE") ;
				  String adresse = rs.getString("VIS_ADRESSE");
				  //this.visiteurs.add(new Visiteur(numero,nom,prenom,adresse)) ;
				  //System.out.println(visiteurs.get(i));
				  //i ++;
				  }
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
	 
	 /*
	public static void main(String [] args) {
			AccesModele test = new AccesModele() ;
	 }
	 */ 
}
