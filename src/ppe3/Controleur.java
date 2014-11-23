package ppe3;

import java.awt.Component;
import java.util.* ;

/** Contrôleur de l'application
 * 
 * @author xilim
 * @param <GuiAppliCR>
 *
 */
public class Controleur {
	
	private GuiAppliCR vuePrincipale ;
	private AccesModele modele ;
	
	/** Créer le contrôleur
	 * 
	 * @param modele Le modèle
	 */
	public Controleur(AccesModele modele) {
		super();
		System.out.println("Controleur::Controleur()") ;
		this.modele = modele;
	}
	
	/** Obtenir la vue principale
	 * 
	 * @return La vue principale
	 */
	public GuiAppliCR getVuePrincipale() {
		System.out.println("Controleur::getVuePrincipale()") ;
		return vuePrincipale ;
	}

	/** Modifier la vue principale
	 * 
	 * @param vuePrincipale La nouvelle vue principale
	 */
	public void setVuePrincipales(GuiAppliCR vuePrincipale) {
		System.out.println("Controleur::setVuePrincipale()") ;
		this.vuePrincipale = vuePrincipale ;
	}
	
	/** Visualiser la liste des visiteurs sous une forme tabulaire
	 * 
	 */
	public void visualiserVisiteurs(){
		System.out.println("Controleur::visualiserVisiteurs()") ;
		this.vuePrincipale.changerDeVue("Liste visiteurs");
	}
	
	/** Visualiser la liste des rapports de visite sous une forme tabulaire
	 * 
	 */
	public void visualiserRapportsVisite(){
		System.out.println("Controleur::visualiserRapportsVisite()") ;
		this.vuePrincipale.changerDeVue("Liste rapports de visite");
	}
	
	/** Visualiser le bilan des rapports de visite
	 * 
	 */
	public void visualiserBilanRapportVisite(){
		System.out.println("Controleur::visualiserBilanRapportVisite()") ;
		this.modele.selectBilanRapportsVisite() ;
		this.vuePrincipale.changerDeVue("Liste rapports de visite") ;
	}
	
	/** Signaler un rapport de visite comme étant "lu"
	 * @param numRapport 
	 * 
	 */
	public void setRapportVisiteLu(String numRapport){
		System.out.println("Controleur::setRapportVisiteLu()");
		this.modele.setReadRapportsVisite(numRapport);
	}
	
	/** Signaler un rapport de visite comme étant "non lu"vuePrincipale
	 * 
	 */
	public void setRapportVisiteNonLu(){
		System.out.println("Controleur::setRapportVisiteNonLu()");
		this.modele.setDontReadRapportsVisite();
	}
	
	/** Modifier le paramètre : visiteur du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public void setVisiteurRapport(String nomVisiteur){
		System.out.println("Controleur::setVisiteurRapport()");
		this.modele.setVisiteurPourRapport(nomVisiteur);
	}
		
	/** Obtenir le paramètre : visiteur du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public String getVisiteurRapport(){
		System.out.println("Controleur::setVisiteurRapport()");
		return this.modele.getVisiteurPourRapport();
	}
	
	/** Modifier le paramètre : mois du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public void setMoisRapport(String moisRapport){
		System.out.println("Controleur::setMoisRapport()");
		this.modele.setMoisPourRapport(moisRapport);
	}
		
	/** Obtenir le paramètre : mois du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public String getMoisRapport(){
		System.out.println("Controleur::getMoisRapport()");
		return this.modele.getMoisPourRapport();
	}
	
	/** Modifier le paramètre : année du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public void setAnneeRapport(String anneeRapport){
		System.out.println("Controleur::setAnneeRapport()");
		this.modele.setAnneePourRapport(anneeRapport);
	}
		
	/** Obtenir le paramètre : année du rapport de visite à partir de la sélection d'un visiteur
	 * 
	 */
	public String getAnneeRapport(){
		System.out.println("Controleur::getAnneeRapport()");
		return this.modele.getAnneePourRapport();
	}
	
	/** Quitter l'application
	 * 
	 */
	public void quitterApplication(){
		System.out.println("Controleur::quitterApplication()") ;
		setRapportVisiteNonLu();
		System.exit(0) ;
	}
	
}
	