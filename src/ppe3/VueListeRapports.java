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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class VueListeRapports extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	
	private ModeleListeRapports modeleTableauRapports ;
	private JTable tableauRapports ;
	private TableRowSorter trieur ;
	
//	private String[] moisList = {"Tous les mois", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
//	private JComboBox moisBox = new JComboBox(moisList) ;
//	private String[] anneeList = {"Toutes les années", "2010", "2011", "2012", "2013", "2014" };
//	private JComboBox anneeBox = new JComboBox(anneeList) ;
//	private JComboBox visiteurBox = new JComboBox() ;
	private ArrayList<String> numVisiteurs = new ArrayList<String>() ;
//	private JButton bRecherche = new JButton("Recherche");
//	public JTextField visiteurField = new JTextField("") ;
//	public JTextField moisField = new JTextField("") ;
//	public JTextField anneeField = new JTextField("") ;

	public VueListeRapports(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListeRapports::VueListeRapports()") ;
		this.modele = modele;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		
//		this.visiteurBox.addItem("Tous les visiteurs");
		
		for(Visiteur visiteur : this.modele.getVisiteurs()){
//			this.visiteurBox.addItem(visiteur.getNom()) ;
			numVisiteurs.add(new String(visiteur.getNumero())) ;
		}		
		
//		boxEtiquette.add(new JLabel("Rapports de visite - Mois : ")) ;
//		boxEtiquette.add(moisField) ;
//		boxEtiquette.add(new JLabel("  - Année : ")) ;
//		boxEtiquette.add(anneeField) ;
//		boxEtiquette.add(new JLabel("  - Visiteur : ")) ;
//		boxEtiquette.add(visiteurField) ;
//		boxEtiquette.add(bRecherche) ;
//		moisField.addActionListener(this);
//		anneeField.addActionListener(this);
//		visiteurField.addActionListener(this);
//		bRecherche.addActionListener(this);
		
		
		boxEtiquette.add(Box.createHorizontalGlue()) ;
	
		modeleTableauRapports = new ModeleListeRapports(modele,controleur) ;
		tableauRapports = new JTable(modeleTableauRapports) ;
		tableauRapports.setRowHeight(30) ;
		trieur = new TableRowSorter(modeleTableauRapports);
		tableauRapports.setRowSorter(trieur);
		
		this.appliquerRendu() ;

		JScrollPane spRapports = new JScrollPane(tableauRapports) ;
		spRapports.setPreferredSize(new Dimension(1090,420)) ;

		boxTableau.add(spRapports) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
				
	}
	
	public void actualiser(){
		System.out.println("VueListeRapports::actualiser()") ;
//		visiteurBox.setSelectedIndex(0);
		modeleTableauRapports = new ModeleListeRapports(modele,controleur) ;
		tableauRapports.setModel(modeleTableauRapports);
//		visiteurField.setText(controleur.getVisiteurRapport());
//		moisField.setText(controleur.getMoisRapport());
//		anneeField.setText(controleur.getAnneeRapport());
		this.appliquerRendu();
	}
	
	private void appliquerRendu(){
		System.out.println("VueListeRapports::appliquerRendu()") ;
		
		this.tableauRapports.getColumn("Bilan").setCellRenderer(new RenduBoutonRapports());
		this.tableauRapports.getColumn("Bilan").setCellEditor(new EditeurBoutonRapports(new JCheckBox())) ; 
	}

	public void actionPerformed(ActionEvent e) {
		Object sourceEvt = e.getSource() ;
				
//		String moisText = moisField.getText();
//		String anneeText = anneeField.getText();
//		String concatText = moisText + "/" + anneeText;
//		String visiteurText = visiteurField.getText();
//		if(moisText.equals("Tous les mois") && anneeText.equals("Toutes les années") && visiteurText.equals("Tous les visiteurs")){
//			trieur.setRowFilter(null);
//		}
//		else if(visiteurText.equals("Tous les visiteurs")){
//			trieur.setRowFilter(RowFilter.regexFilter(concatText, 3));
//		}
//		else if(moisText.equals("Tous les mois") && anneeText.equals("Toutes les années")){
//			trieur.setRowFilter(RowFilter.regexFilter(visiteurText, 0));
//			System.out.println(visiteurText);
//		}
//		else{
//			final RowFilter<ModeleListeRapports, String> premierFiltre = RowFilter.regexFilter(concatText, 3);
//			final RowFilter<ModeleListeRapports, String> troisiemeFiltre = RowFilter.regexFilter(visiteurText, 0);
//			
//			final List<RowFilter<ModeleListeRapports, String>> filtres = new ArrayList<RowFilter<ModeleListeRapports, String>>();
//			filtres.add(premierFiltre);
//			filtres.add(troisiemeFiltre);
//			
//			final RowFilter<ModeleListeRapports, String>  compoundRowFilter = RowFilter.andFilter(filtres);
//			trieur.setRowFilter(compoundRowFilter);
//		}
//		
	}
	
}
