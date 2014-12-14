package ppe3;

import java.awt.Component;
import java.sql.SQLException;
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
	
	/** Visualiser la page de connexion
	 * 
	 */
	public void visualiserDetailRapport(){
		System.out.println("Controleur::visualiserDetailRapport()") ;
		this.vuePrincipale.changerDeVue("Vue détail rapport de visite");
	}
	
	/** Visualiser la page de connexion
	 * 
	 */
	public void visualiserConnexion(){
		System.out.println("Controleur::visualiserConnexion()") ;
		this.vuePrincipale.changerDeVue("Vue connexion");
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
	
	/** Modifier le paramètre : numéro du rapport de visite à partir de la sélection d'un rapport
	 * 
	 */
	public void setNumRapport(String numeroRapport){
		System.out.println("Controleur::setVisiteurRapport()");
		this.modele.setMatRap(numeroRapport);
	}
		
	/** Obtenir le paramètre : numéro du rapport de visite à partir de la sélection d'un rapport
	 * 
	 */
	public String getNumRapport(){
		System.out.println("Controleur::setVisiteurRapport()");
		return this.modele.getMatRap();
	}
	
	/** Créer la liste des rapports de visite
	 * 
	 */
	public void creerRapportVisite(String matDeg, int year, int month){
		System.out.println("Controleur::creerRapportVisite");
		this.modele.remplirRapports(matDeg, year, month);
	}
	
	/** Quitter l'application
	 * 
	 */
	public void quitterApplication(){
		System.out.println("Controleur::quitterApplication()") ;
		setRapportVisiteNonLu();
		System.exit(0) ;
	}

	/** Connecter un délégué régional
	 *
	 * @param sLogin L'identifiant du délégué
	 * @param sPasswd Le mot de passe du délégué
	 * @throws SQLException Peut générer une exception sql
	 * @return Vrai si il existe un délégué avec cet indentifiant et mot de passe
	 */
	public boolean seConnecter(String sLogin, String sPasswd) throws SQLException {
		boolean bSuccess = true;
		System.out.println("Controleur::seConnecter()") ;
		boolean coOK = this.modele.seConnecter(sLogin,sPasswd) ;
		if (coOK == true){
			this.vuePrincipale.etatMenu(1);
			this.vuePrincipale.changerDeVue("Liste visiteurs") ;
			System.out.println("Controleur::seConnecter()::identifiants corrects") ;
		}
		else {
			bSuccess = false;
			System.out.println("Controleur::seConnecter()::identifiants incorrects") ;
		}
		return bSuccess;
	}

	/** Déconnecter l'utilisateur courant
	 * @throws SQLException Peut générer une exception sql
	 */
	public void deconnexion() throws SQLException {
		System.out.println("Controleur::deconnexion()") ;
		this.vuePrincipale.etatMenu(0);
		this.vuePrincipale.changerDeVue("Vue connexion") ;
	}
	
}
	