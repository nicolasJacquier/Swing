package ppe3;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VueDetailRapports extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	private String matRap ;
	
	private ModeleDetailRapports modeleTableauDetailRapports ;
	private JTable tableauDetailRapports ;
	
	public VueDetailRapports(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueDetailRapports::VueDetailRapports()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		boxEtiquette.add(new JLabel("Détails sur le compte-rendu sélectionné."));
		boxEtiquette.add(Box.createHorizontalGlue()) ;
		modeleTableauDetailRapports = new ModeleDetailRapports(modele, controleur) ;
		tableauDetailRapports = new JTable(modeleTableauDetailRapports) ;
		tableauDetailRapports.setRowHeight(30) ;
		JScrollPane spInfoCR = new JScrollPane(tableauDetailRapports) ;
		spInfoCR.setPreferredSize(new Dimension(1090,420)) ;
		boxTableau.add(spInfoCR) ;
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		this.add(boxPrincipal) ;
	}
	
	public void actualiser(){
		modeleTableauDetailRapports = new ModeleDetailRapports(modele, controleur) ;
		tableauDetailRapports.setModel(modeleTableauDetailRapports) ;
	}
}
