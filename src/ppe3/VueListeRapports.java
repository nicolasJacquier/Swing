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
	
	private String[] moisList = {"Tous les mois", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private JComboBox moisBox = new JComboBox(moisList) ;
	private String[] anneeList = {"Toutes les années", "2010", "2011", "2012", "2013", "2014" };
	private JComboBox anneeBox = new JComboBox(anneeList) ;
	private JComboBox visiteurBox = new JComboBox() ;
	private ArrayList<String> numVisiteurs = new ArrayList<String>() ;
	private JButton bRecherche = new JButton("Recherche") ;
	private JTextField dateChoix = new JTextField("") ;
	private JTextField visiteurChoix = new JTextField("") ;

	public VueListeRapports(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListeRapports::VueListeRapports()") ;
		this.modele = modele;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		
		this.visiteurBox.addItem("Tous les visiteurs");
		
		for(Visiteur visiteur : this.modele.getVisiteurs()){
			this.visiteurBox.addItem(visiteur.getNom()) ;
			numVisiteurs.add(new String(visiteur.getNumero())) ;
		}		
		
		boxEtiquette.add(new JLabel("Rapports de visite - Mois : ")) ;
		boxEtiquette.add(moisBox) ;
		boxEtiquette.add(new JLabel("  - Année : ")) ;
		boxEtiquette.add(anneeBox) ;
		boxEtiquette.add(new JLabel("  - Visiteur : ")) ;
		boxEtiquette.add(visiteurBox) ;
//		boxEtiquette.add(new JLabel("  -  ")) ;
//		boxEtiquette.add(bRecherche);
//		bRecherche.addActionListener(this);
		moisBox.addActionListener(this);
		anneeBox.addActionListener(this);
		visiteurBox.addActionListener(this);
		
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
		visiteurBox.setSelectedIndex(0);
		modeleTableauRapports = new ModeleListeRapports(modele,controleur) ;
		tableauRapports.setModel(modeleTableauRapports);
		this.appliquerRendu();
	}
	
	private void appliquerRendu(){
		System.out.println("VueListeRapports::appliquerRendu()") ;
		
		this.tableauRapports.getColumn("Bilan").setCellRenderer(new RenduBoutonRapports());
		this.tableauRapports.getColumn("Bilan").setCellEditor(new EditeurBoutonRapports(new JCheckBox())) ; 
	}
	
	public void actionPerformed(ActionEvent e) {
		Object sourceEvt = e.getSource() ;
				
		String moisText = moisBox.getSelectedItem().toString();
		String anneeText = anneeBox.getSelectedItem().toString();
		String concatText = moisText + "/" + anneeText;
		String visiteurText = visiteurBox.getSelectedItem().toString();
		if(moisBox.getSelectedItem().equals("Tous les mois") && anneeBox.getSelectedItem().equals("Toutes les années") && visiteurBox.getSelectedItem().equals("Tous les visiteurs")){
			trieur.setRowFilter(null);
		}
		else if(visiteurText.equals("Tous les visiteurs")){
			trieur.setRowFilter(RowFilter.regexFilter(concatText, 3));
		}
		else if(moisBox.getSelectedItem().equals("Tous les mois") && anneeBox.getSelectedItem().equals("Toutes les années")){
			trieur.setRowFilter(RowFilter.regexFilter(visiteurText, 0));
			System.out.println(visiteurText);
		}
		else{
			final RowFilter<ModeleListeRapports, String> premierFiltre = RowFilter.regexFilter(concatText, 3);
			final RowFilter<ModeleListeRapports, String> troisiemeFiltre = RowFilter.regexFilter(visiteurText, 0);
			
			final List<RowFilter<ModeleListeRapports, String>> filtres = new ArrayList<RowFilter<ModeleListeRapports, String>>();
			filtres.add(premierFiltre);
			filtres.add(troisiemeFiltre);
			
			final RowFilter<ModeleListeRapports, String>  compoundRowFilter = RowFilter.andFilter(filtres);
			trieur.setRowFilter(compoundRowFilter);
		}
		
	}
	
}
