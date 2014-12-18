package ppe3;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** Représenter un Praticien de l'agence
 * 
 * @author xilim
 *
 */
public class PraticienH {
	private String nom ;
	private String ville ;
	private int coefC ;
	private int coefN ;
	private Date rapDate;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCoefC() {
		return coefC;
	}

	public void setCoefC(int coefC) {
		this.coefC = coefC;
	}

	public int getCoefN() {
		return coefN;
	}

	public void setCoefN(int coefN) {
		this.coefN = coefN;
	}

	public PraticienH(String nom, String ville, int coefC, Date rapDate, int coefN) {
		super();
		this.nom = nom;
		this.ville = ville;
		this.coefC = coefC;
		this.coefN = coefN;
		this.rapDate = rapDate;
	}



	

	public String toString() {
		return "Praticien Hesitant[nom=" + nom + ", ville=" + ville + ", coefC="
				+ coefC + ",rapDate=" +rapDate+",  coefN=" + coefN + "]";
	}
	
	
	
	public long getTempsEcoule() {
		
		
		Calendar dateToday = Calendar.getInstance();
		long today = dateToday.getTimeInMillis();
		
		Calendar dateRapport = Dates.DateToCalendar(rapDate);
		long rapport = dateRapport.getTimeInMillis();
		
		long tempsEcoule = (((today-rapport) /3600000))/24;
		
		return tempsEcoule ;
		
	}

}
