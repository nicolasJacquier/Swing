package ppe3;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/** Vue principale 
 * 
 * @author xilim
 *
 */
public class GuiAppliCR extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private AccesModele modele ;
	private Controleur controleur ;
	
	private JMenuBar barreMenus ;
		
	private JMenuItem itemQuitter ;
	private JMenuItem itemDeconnexion ;
	
	private JMenuItem itemVisualiserVisiteurs;
	private JMenuItem itemVisualiserRapports;
	private JMenuItem itemVisualiserPraticiensH ;
	
	private JMenu menuFichier ;
	private JMenu menuVisiteurs ;
	private JMenu menuRapports ;
	private JMenu menuPraticiensH ;

	private VueListePraticiensH vueVisualiserPraticiensH ;	
	private VueListeVisiteurs vueVisualiserVisiteurs ;
	private VueListeRapports vueVisualiserRapports ;
	private VueDetailRapports vueDetailRapports ;
	private VueConnexion vueConnexion ;
	
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
//		this.setSize(1300,510) ; 
		this.setSize(1120,520) ;
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		
		this.vues = new CardLayout(2,2) ;
		this.conteneur = this.getContentPane() ;
		this.conteneur.setLayout(this.vues) ;
		
		vueVisualiserVisiteurs = new VueListeVisiteurs(modele,controleur) ;
		vueVisualiserRapports = new VueListeRapports(modele,controleur);
		vueDetailRapports = new VueDetailRapports(modele,controleur);
		vueConnexion = new VueConnexion(modele,controleur);
		vueVisualiserPraticiensH = new VueListePraticiensH(modele,controleur) ;
		
		this.conteneur.add(vueVisualiserVisiteurs,"Liste visiteurs") ;
		this.conteneur.add(vueVisualiserRapports,"Liste rapports de visite") ;
		this.conteneur.add(vueDetailRapports,"Vue détail rapport de visite") ;
		this.conteneur.add(vueVisualiserPraticiensH,"Liste des Praticiens Hesitants") ;
		this.conteneur.add(vueConnexion,"Vue connexion") ;
		
		this.vues.show(this.conteneur, "Vue connexion");
		
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
		barreMenus = new JMenuBar() ;
		
		menuFichier = new JMenu("Fichier") ;
		this.itemDeconnexion = new JMenuItem("Deconnexion") ;
		this.itemDeconnexion.setEnabled(false);
		this.itemDeconnexion.addActionListener(this) ;
		menuFichier.add(this.itemDeconnexion) ;
		this.itemQuitter = new JMenuItem("Quitter") ;
		this.itemQuitter.addActionListener(this) ;
		menuFichier.add(this.itemQuitter) ;
		
		menuVisiteurs = new JMenu("Visiteurs") ;
		this.itemVisualiserVisiteurs = new JMenuItem("Liste des visiteurs") ;
		this.itemVisualiserVisiteurs.addActionListener(this) ;
		menuRapports = new JMenu("Rapports de visite");
		this.itemVisualiserRapports = new JMenuItem("Liste des rapports de visite");
		this.itemVisualiserRapports.addActionListener(this);
		menuPraticiensH = new JMenu("Praticiens Hesitants");
		this.itemVisualiserPraticiensH = new JMenuItem("Liste des Praticiens Hesitants") ;
		this.itemVisualiserPraticiensH.addActionListener(this) ;
		menuPraticiensH.add(this.itemVisualiserPraticiensH) ;
		menuRapports.add(this.itemVisualiserRapports) ;
		menuVisiteurs.add(this.itemVisualiserVisiteurs) ;
		
		menuVisiteurs.setEnabled(false);
		menuRapports.setEnabled(false);
		menuPraticiensH.setEnabled(false);
		
		barreMenus.add(menuFichier) ;
		barreMenus.add(menuVisiteurs) ;
		barreMenus.add(menuRapports);
		barreMenus.add(menuPraticiensH) ;
		
		this.setJMenuBar(barreMenus) ;
	}
	
	public JMenuBar getBarreMenus() {
		return barreMenus;
	}

	public void setBarreMenus(JMenuBar barreMenus) {
		this.barreMenus = barreMenus;
	}

	public JMenu getMenuFichier() {
		return menuFichier;
	}

	public JMenu getMenuPraticiensH() {
		return menuPraticiensH;
	}

	public void setMenuPraticiensH(JMenu menuPraticiensH) {
		this.menuPraticiensH = menuPraticiensH;
	}

	public void setMenuFichier(JMenu menuFichier) {
		this.menuFichier = menuFichier;
	}

	public JMenu getMenuVisiteurs() {
		return menuVisiteurs;
	}

	public void setMenuVisiteurs(JMenu menuVisiteurs) {
		this.menuVisiteurs = menuVisiteurs;
	}

	public JMenu getMenuRapports() {
		return menuRapports;
	}

	public void setMenuRapports(JMenu menuRapports) {
		this.menuRapports = menuRapports;
	}
	
	public JMenuItem getItemDeconnexion() {
		return itemDeconnexion;
	}

	public void setItemDeconnexion(JMenuItem itemDeconnexion) {
		this.itemDeconnexion = itemDeconnexion;
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
		else if(nomVue.equals("Liste rapports de visite")){
			this.vueVisualiserRapports.actualiser() ;
		}
		else if(nomVue.equals("Vue connexion")){
			this.vueConnexion.actualiser() ;
		}
		else if(nomVue.equals("Vue détail rapport de visite")){
			this.vueDetailRapports.actualiser() ;
		}
		else if(nomVue.equals("Liste des Praticiens Hesitants")){
			this.vueVisualiserPraticiensH.actualiser(); 
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
		else if(sourceEvt == this.itemVisualiserPraticiensH){
			this.controleur.visualiserPraticiensH() ;
		}
		else if(sourceEvt == this.itemDeconnexion){
			try {
				int iSelected = JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment vous déconnecter ?", "Déconnexion", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(iSelected == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null,"Vous êtes déconnecté", "Déconnexion réussie",JOptionPane.INFORMATION_MESSAGE);
					this.itemDeconnexion.setEnabled(false);
					this.modele.resetData();
					this.controleur.deconnexion() ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Rendre les menus disponibles
	 * 
	 * @param etat Numéro faisant office d'état du menu
	 */
	public void etatMenu(int etat){
		if(etat == 1) {
			this.getMenuVisiteurs().setEnabled(true);
			this.getMenuRapports().setEnabled(true);
			this.getMenuPraticiensH().setEnabled(true);
			this.getItemDeconnexion().setEnabled(true);
		}
		else {
			this.getMenuVisiteurs().setEnabled(false);
			this.getMenuRapports().setEnabled(false);
			this.getMenuPraticiensH().setEnabled(false);
			this.getItemDeconnexion().setEnabled(false);
		}
	}
	
}