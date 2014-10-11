package ppe3;

import java.util.* ;
import java.awt.FlowLayout;

import java.awt.* ;
import javax.swing.* ;
import javax.swing.JTable.* ;

/** Vue dédiée à l'affichage de la liste des clients
 * 
 * @author xilim
 *
 */
public class VueListeVisiteurs extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	
	private ModeleListeVisiteurs modeleTableauVisiteurs ;
	private JTable tableauVisiteurs ;
	
	/** Créer la vue dédiée à l'affichage de la liste des clients
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public VueListeVisiteurs(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListeVisiteurs::VueListeVisiteurs()") ;
		
		this.modele = modele ;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;

		boxEtiquette.add(new JLabel("Visiteurs :")) ;
		boxEtiquette.add(Box.createHorizontalGlue()) ;
		
		modeleTableauVisiteurs = new ModeleListeVisiteurs(modele) ;
		tableauVisiteurs = new JTable(modeleTableauVisiteurs) ;
		tableauVisiteurs.setRowHeight(30) ;
		
		JScrollPane spVisiteurs = new JScrollPane(tableauVisiteurs) ;
		//spClients.setPreferredSize(new Dimension(1290,420)) ;
		spVisiteurs.setPreferredSize(new Dimension(1090,420)) ;
		
		boxTableau.add(spVisiteurs) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
		
	}
	
	/** Actualiser le modèle du tableau
	 * 
	 */
	public void actualiser(){
		System.out.println("VueListeVisiteurs::actualiser()") ;
		modeleTableauVisiteurs = new ModeleListeVisiteurs(modele) ;
		tableauVisiteurs.setModel(modeleTableauVisiteurs) ;
	}
	
}
