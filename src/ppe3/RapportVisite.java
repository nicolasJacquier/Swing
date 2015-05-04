package ppe3;

import java.util.GregorianCalendar;

public class RapportVisite {
	private String rapNum;
	private GregorianCalendar dateVisite;
	private String bilan;
	private String motif;
	private String praticienVille;
	private String praticienNom;
	private String visiteurMat;
	private String visiteurNom;
	private GregorianCalendar dateRedac;
	private String lu;
	
	/** Créer un rapport de visite
	 * 
	 * @param rapNum Le numéro du rapport
	 * @param praticienNom Le nom du praticien
	 * @param visiteurMat Le matricule du visiteur
	 * @param visiteurNom Le nom du visiteur
	 * @param dateVisite La date de visite
	 * @param dateRedac La date de rédaction
	 * @param praticienVille La ville du praticien
	 * @param bilan Le contenu du bilan
	 * @param lu Informe si le bilan a été lu
	 */
	public RapportVisite(String rapNum, String praticienNom, String visiteurNom, String visiteurMat, GregorianCalendar dateVisite, GregorianCalendar dateRedac, String praticienVille, String bilan, String lu) {
		super();
		this.rapNum = rapNum;
		this.praticienNom = praticienNom;
		this.visiteurMat = visiteurMat;
		this.visiteurNom = visiteurNom;
		this.dateVisite = dateVisite;
		this.dateRedac = dateRedac;
		this.praticienVille = praticienVille;
		this.bilan = bilan;
		this.lu = lu;
	}
	/** Créer un rapport de visite
	 * 
	 * @param rapNum Le numéro du rapport
	 * @param praticienNom Le nom du praticien
	 * @param visiteurMat Le matricule du visiteur
	 * @param dateVisite La date de visite
	 * @param dateRedac La date de rédaction
	 * @param praticienVille La ville du praticien
	 * @param bilan Le contenu du bilan
	 * @param lu Informe si le bilan a été lu
	 */
	public RapportVisite(String rapNum, String praticienNom, String visiteurMat, GregorianCalendar dateVisite, GregorianCalendar dateRedac, String praticienVille, String bilan, String lu) {
		super();
		this.rapNum = rapNum;
		this.praticienNom = praticienNom;
		this.visiteurMat = visiteurMat;
		this.dateVisite = dateVisite;
		this.dateRedac = dateRedac;
		this.praticienVille = praticienVille;
		this.bilan = bilan;
		this.lu = lu;
	}
	
	/** Créer un rapport de visite
	 * 
	 * @param rapNum Le numéro du rapport
	 * @param praticienNom Le nom du praticien
	 * @param visiteurMat Le matricule du visiteur
	 * @param dateVisite La date de visite
	 * @param dateRedac La date de rédaction
	 * @param praticienVille La ville du praticien
	 */
	public RapportVisite(String rapNum, String praticienNom, String visiteurMat, GregorianCalendar dateVisite, GregorianCalendar dateRedac, String praticienVille) {
		super();
		this.rapNum = rapNum;
		this.praticienNom = praticienNom;
		this.visiteurMat = visiteurMat;
		this.dateVisite = dateVisite;
		this.dateRedac = dateRedac;
		this.praticienVille = praticienVille;
		bilan = "" ;
		lu = "Non" ;
	}
	
	/** Créer un rapport de visite
	 * 
	 * @param rapNum Le numéro du rapport
	 * @param praticien Le praticien
	 * @param visiteur Le visiteur
	 * @param dateVisite La date de visite
	 */
	public RapportVisite(String rapNum, String praticienNom, String visiteurMat, GregorianCalendar dateVisite) {
		super();
		this.rapNum = rapNum;
		this.praticienNom = praticienNom;
		this.visiteurMat = visiteurMat;
		this.dateVisite = dateVisite;
		praticienVille = "Non renseigné";
		bilan = "" ;
		lu = "Non" ;
	}
	
	/** Créer un rapport de visite
	 * 
	 * Visite ce jour
	 * @param rapNum Le numéro du rapport
	 * @param praticien Le praticien
	 * @param visiteur Le visiteur
	 */
	public RapportVisite(String rapNum, String praticienNom, String visiteurMat) {
		super();
		this.rapNum = rapNum;
		this.praticienNom = praticienNom;
		this.visiteurMat = visiteurMat;
		this.dateVisite = new GregorianCalendar();
		this.dateRedac = new GregorianCalendar();
		praticienVille = "Non renseigné";
		bilan = "" ;
		lu = "Non" ;
	}

	public String getPraticienVille() {
		return praticienVille;
	}

	public void setPraticienVille(String praticienVille) {
		this.praticienVille = praticienVille;
	}

	@Override
	public String toString() {
		String visite ;
		if(this.dateVisite != null){
			visite = Dates.parseDate(this.dateVisite) ;
		}
		else {
			visite = "" ;
		}
		
		String visite2 ;
		if(this.dateRedac != null){
			visite2 = Dates.parseDate(this.dateRedac) ;
		}
		else {
			visite2 = "" ;
		}
		
		return "RapportVisite [dateVisite=" + visite
				+ ", dateRedac= " + visite2 +", bilan=" + bilan + ", motif=" 
				+ motif + ", lu="+ lu + ", praticien=" + praticienNom
				+ " " + praticienNom + ", visiteur=" + visiteurMat + "]";
	}

	public GregorianCalendar getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(GregorianCalendar dateVisite) {
		this.dateVisite = dateVisite;
	}

	public String getBilan() {
		return bilan;
	}

	public void setBilan(String bilan) {
		this.bilan = bilan;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public GregorianCalendar getDateRedac() {
		return dateRedac;
	}

	public void setDateRedac(GregorianCalendar dateRedac) {
		this.dateRedac = dateRedac;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getPraticienNom() {
		return praticienNom;
	}

	public void setPraticienNom(String praticienNom) {
		this.praticienNom = praticienNom;
	}

	public String getVisiteurMat() {
		return visiteurMat;
	}

	public void setVisiteurMat(String visiteurMat) {
		this.visiteurMat = visiteurMat;
	}

	public String getRapNum() {
		return rapNum;
	}

	public void setRapNum(String rapNum) {
		this.rapNum = rapNum;
	}
	
	public String getVisiteurNom() {
		return visiteurNom;
		
	}
	public void setVisiteurNom(String visiteurNom) {
		this.visiteurNom = visiteurNom;
	}
	
}
