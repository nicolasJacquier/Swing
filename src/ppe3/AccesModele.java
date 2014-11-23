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
	
	private String visiteur = "";
	private String annee = "";
	private String mois = "";
	
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
	 
	 public void selectVisiteurs() {
		  System.out.println("Remplissage Visiteurs :");
		  String query = "SELECT VIS_MATRICULE, VIS_NOM, VIS_PRENOM, VIS_ADRESSE, VIS_CP FROM VISITEUR";
		  try {
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(query);
//			  int i = 0;
			  while (rs.next()) {
				  String numero = rs.getString("VIS_MATRICULE");
				  String nom = rs.getString("VIS_NOM") ;
				  String prenom = rs.getString("VIS_PRENOM") ;
				  String adresse = rs.getString("VIS_ADRESSE");
				  String numCp = rs.getString("VIS_CP");
				  this.visiteurs.add(new Visiteur(numero,nom,prenom,adresse,numCp)) ;
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
		  		+ "RAPPORT_VISITE.VIS_MATRICULE, RAPPORT_VISITE.RAP_NUM,"
		  		+ "VISITEUR.VIS_NOM "
		  		+ "FROM RAPPORT_VISITE, PRATICIEN, VISITEUR "
		  		+ "WHERE RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM "
		  		+ "AND RAPPORT_VISITE.VIS_MATRICULE=VISITEUR.VIS_MATRICULE";
		  try {
			  PreparedStatement ps = conn.prepareStatement(query);
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

	public void setVisiteurPourRapport(String nomVisiteur) {
		visiteur = nomVisiteur ;
	}
	
	public String getVisiteurPourRapport() {
		return visiteur ;
	}
	
	public void setAnneePourRapport(String anneeField) {
		annee = anneeField ;
	}
	
	public String getAnneePourRapport() {
		return annee ;
	}
	
	public void setMoisPourRapport(String moisField) {
		mois = moisField ;
	}
	
	public String getMoisPourRapport() {
		return mois ;
	}
}
