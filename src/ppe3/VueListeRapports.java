package ppe3;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VueListeRapports extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	
	private ModeleListeRapports modeleTableauRapports ;
	private JTable tableauRapports ;
	
	public VueListeRapports(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListeRapports::VueListeRapports()") ;
		this.modele = modele;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
				
		boxEtiquette.add(new JLabel("Rapports :")) ;
		boxEtiquette.add(Box.createHorizontalGlue()) ;
	
		modeleTableauRapports = new ModeleListeRapports(modele,controleur) ;
		tableauRapports = new JTable(modeleTableauRapports) ;
		tableauRapports.setRowHeight(30) ;
		
		this.appliquerRendu() ;

		JScrollPane spRapports = new JScrollPane(tableauRapports) ;
		//spLocations.setPreferredSize(new Dimension(1290,420)) ;
		spRapports.setPreferredSize(new Dimension(1090,420)) ;

		boxTableau.add(spRapports) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
	}
	
	public void actualiser(){
		System.out.println("VueListeRapports::actualiser()") ;
		modeleTableauRapports = new ModeleListeRapports(modele,controleur) ;
		tableauRapports.setModel(modeleTableauRapports);
		this.appliquerRendu();
	}
	
	private void appliquerRendu(){
		System.out.println("VueListeRapports::appliquerRendu()") ;
		//this.tableauRapports.getColumn("Nom Praticien") ;
		//this.tableauRapports.getColumn("Ville") ;
		//this.tableauRapports.getColumn("Date visite") ;
		//this.tableauRapports.getColumn("Lu") ;
		
		this.tableauRapports.getColumn("Bilan").setCellRenderer(new RenduBoutonRapports());
		this.tableauRapports.getColumn("Bilan").setCellEditor(new EditeurBoutonRapports(new JCheckBox())) ; 
	}
	
}
