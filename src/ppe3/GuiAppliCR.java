package ppe3;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** Vue principale 
 * 
 * @author xilim
 *
 */
public class GuiAppliCR extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private AccesModele modele ;
	private Controleur controleur ;
		
	private JMenuItem itemQuitter ;
	private JMenuItem itemConnexion ;
	private JMenuItem itemDeconnexion ;
	
	private JMenuItem itemVisualiserVisiteurs;
	private JMenuItem itemVisualiserRapports;
	
	private VueListeVisiteurs vueVisualiserVisiteurs ;
	private VueListeRapports vueVisualiserRapports ;
	
	private CardLayout vues ;
	private Container conteneur ;
	

	/** Construire la vue principale de l'application
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public GuiAppliCR(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("GuiAppliCR::GuiAppliCR()") ;
		this.modele = modele;
		this.controleur = controleur ;
		this.controleur.setVuePrincipales(this);
		
		this.setTitle("AppliCR") ;
		//this.setSize(1300,500) ; 
		this.setSize(1120,520) ;
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		
		this.vues = new CardLayout(2,2) ;
		this.conteneur = this.getContentPane() ;
		this.conteneur.setLayout(this.vues) ;
		
		vueVisualiserVisiteurs = new VueListeVisiteurs(modele,controleur) ;
		vueVisualiserRapports = new VueListeRapports(modele,controleur);
		
		this.conteneur.add(vueVisualiserVisiteurs,"Liste visiteurs") ;
		this.conteneur.add(vueVisualiserRapports,"Liste rapports") ;
		
		this.vues.show(this.conteneur, "Liste rapports");
		
		this.creerBarreMenus() ;
		this.setVisible(true) ;
		
	}
	
	/** Obtenir le contrôleur
	 * 
	 * @return Le contrôleur
	 */
	public Controleur getControleur() {
		System.out.println("GuiRentaco::getControleur()") ;
		return this.controleur;
	}

	/** Modifier le contrôleur
	 * 
	 * @param controleur Le nouveau contrôleur
	 */
	public void setControleur(Controleur controleur) {
		System.out.println("GuiAppliCR::setControleur()") ;
		this.controleur = controleur;
	}

	/** Obtenir le modèle
	 * 
	 * @return Le modèle
	 */
	public AccesModele getModele() {
		System.out.println("GuiAppliCR::getModele()") ;
		return this.modele;
	}

	/** Modifier le modèle
	 * 
	 * @param modele Le nouveau modèle
	 */
	public void setModele(AccesModele modele) {
		System.out.println("GuiAppliCR::setModele()") ;
		this.modele = modele;
	}

	/** Créer la barre de menus
	 * 
	 */
	private void creerBarreMenus(){
		System.out.println("GuiAppliCR::creerBarreMenus()") ;
		JMenuBar barreMenus = new JMenuBar() ;
		
		JMenu menuFichier = new JMenu("Fichier") ;
		this.itemConnexion = new JMenuItem("Connexion") ;
		this.itemConnexion.addActionListener(this) ;
		menuFichier.add(this.itemConnexion) ;
		this.itemDeconnexion = new JMenuItem("Deconnexion") ;
		this.itemDeconnexion.addActionListener(this) ;
		menuFichier.add(this.itemDeconnexion) ;
		this.itemQuitter = new JMenuItem("Quitter") ;
		this.itemQuitter.addActionListener(this) ;
		menuFichier.add(this.itemQuitter) ;
		
		JMenu menuVisiteurs = new JMenu("Visiteurs") ;
		this.itemVisualiserVisiteurs = new JMenuItem("Liste des visiteurs") ;
		this.itemVisualiserVisiteurs.addActionListener(this) ;
		JMenu menuRapports = new JMenu("Rapports de visite");
		this.itemVisualiserRapports = new JMenuItem("Liste des rapports de visite");
		this.itemVisualiserRapports.addActionListener(this);
		menuRapports.add(this.itemVisualiserRapports) ;
		menuVisiteurs.add(this.itemVisualiserVisiteurs) ;
		
		barreMenus.add(menuFichier) ;
		barreMenus.add(menuVisiteurs) ;
		barreMenus.add(menuRapports);
		
		this.setJMenuBar(barreMenus) ;
	}
	
	/** Basculer sur l'une des vues
	 * 
	 * @param nomVue La vue qui doit passer au premier plan
	 */
	public void changerDeVue(String nomVue){
		System.out.println("GuiAppliCR::changerVue()") ;
		if(nomVue.equals("Liste visiteurs")){
			this.vueVisualiserVisiteurs.actualiser() ;
		}
		else if(nomVue.equals("Liste rapports")){
			this.vueVisualiserRapports.actualiser() ;
		}
		this.vues.show(this.conteneur,nomVue) ;
	}

	/** Gérer les actions de l'utilisateur
	 * 
	 * @param evenement L'événement utilisateur
	 */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		System.out.println("----------------------------------------") ;
		System.out.println("GuiAppliCR::actionPerformed()") ;
		Object sourceEvt = evenement.getSource() ;
		
		if(sourceEvt == this.itemQuitter){
			this.controleur.quitterApplication() ;
		}
		else if(sourceEvt == this.itemVisualiserVisiteurs){
			this.controleur.visualiserVisiteurs() ;
		}
		else if(sourceEvt == this.itemVisualiserRapports){
			this.controleur.visualiserRapportsVisite() ;
		}
	}
}