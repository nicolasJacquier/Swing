package ppe3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VueListeRapports extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	
	private ModeleListeRapports modeleTableauRapports ;
	private ModeleListeRapportsModifie modeleTableauRapportsModifie ;
	private JTable tableauRapports ;
	
	private String[] moisList = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
	private JComboBox moisBox = new JComboBox(moisList) ;
	private String[] anneeList = { "2010", "2011", "2012", "2013", "2014" };
	private JComboBox anneeBox = new JComboBox(anneeList) ;
	private JComboBox visiteurBox = new JComboBox() ;
	private ArrayList<String> numVisiteurs = new ArrayList<String>() ;
	private String moisChoix ;
	private String anneeChoix ;
	private String visiteurChoix ;
	private JButton bRecherche = new JButton("Recherche") ;

	public VueListeRapports(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListeRapports::VueListeRapports()") ;
		this.modele = modele;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
				
		for(Visiteur visiteur : this.modele.getVisiteurs()){
			this.visiteurBox.addItem(visiteur.getNom() + " " + visiteur.getPrenom() + " (" + visiteur.getNumero() + ")") ;
			numVisiteurs.add(new String(visiteur.getNumero())) ;
		}
		
		boxEtiquette.add(new JLabel("Rapports de visite - Mois : ")) ;
		boxEtiquette.add(moisBox) ;
		boxEtiquette.add(new JLabel(" Année : ")) ;
		boxEtiquette.add(anneeBox) ;
		boxEtiquette.add(new JLabel(" Visiteur : ")) ;
		boxEtiquette.add(visiteurBox) ;
		boxEtiquette.add(new JLabel("  -  ")) ;
		boxEtiquette.add(bRecherche);
		
		moisBox.addActionListener(this);
		anneeBox.addActionListener(this);
		visiteurBox.addActionListener(this);
		bRecherche.addActionListener(this);
		
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
		moisBox.setSelectedIndex(0);
		anneeBox.setSelectedIndex(0);
		visiteurBox.setSelectedIndex(0);
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
	
	public void actionPerformed(ActionEvent e) {
		Object sourceEvt = e.getSource() ;
		if(sourceEvt == this.bRecherche){
			Object selected = moisBox.getSelectedItem();
			if(selected.equals("Janvier")){moisChoix = "01" ;}
			else if(selected.equals("Février")){moisChoix = "02" ;}
			else if(selected.equals("Mars")){moisChoix = "03" ;}
			else if(selected.equals("Avril")){moisChoix = "04" ;}
			else if(selected.equals("Mai")){moisChoix = "05" ;}
			else if(selected.equals("Juin")){moisChoix = "06" ;}
			else if(selected.equals("Juillet")){moisChoix = "07" ;}
			else if(selected.equals("Août")){moisChoix = "08" ;}
			else if(selected.equals("Septembre")){moisChoix = "09" ;}
			else if(selected.equals("Octobre")){moisChoix = "10" ;}
			else if(selected.equals("Novembre")){moisChoix = "11" ;}
			else if(selected.equals("Décembre")){moisChoix = "12" ;}
					
		
//		if(anneeBox == (JComboBox) e.getSource()){
			Object selected2 = anneeBox.getSelectedItem();
			if(selected2.equals("2010")){anneeChoix = "2010" ;}
			else if(selected2.equals("2011")){anneeChoix = "2011" ;}
			else if(selected2.equals("2012")){anneeChoix = "2012" ;}
			else if(selected2.equals("2013")){anneeChoix = "2013" ;}
			else if(selected2.equals("2014")){anneeChoix = "2014" ;}
			
		

//		if(visiteurBox == (JComboBox) e.getSource()){
			int indiceVisiteurs = visiteurBox.getSelectedIndex() ; 
			visiteurChoix = (String) numVisiteurs.get(indiceVisiteurs) ;
			modele.getRapportsVisiteModifie().clear();
			modele.selectChoixRapportsVisite(anneeChoix, moisChoix, visiteurChoix);
			modeleTableauRapportsModifie = new ModeleListeRapportsModifie(modele,controleur) ;
			tableauRapports.setModel(modeleTableauRapportsModifie);
			this.appliquerRendu();
			//		System.out.println(indiceVisiteurs);
			System.out.println(anneeChoix);
			System.out.println(moisChoix);
			System.out.println(visiteurChoix);
			
		}
		
	}
	
}
