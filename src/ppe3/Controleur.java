package ppe3;

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
	
	/** Quitter l'application
	 * 
	 */
	public void quitterApplication(){
		System.out.println("Controleur::quitterApplication()") ;
		System.exit(0) ;
	}
	
}
	