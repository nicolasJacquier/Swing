package ppe3;

import java.util.* ;
import java.awt.* ;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.* ;
import javax.swing.JTable.* ;
import javax.swing.table.TableRowSorter;

/** Vue dédiée à l'affichage de la liste des Praticiens Hesitants
 * 
 * @author xilim
 *
 */
public class VueListePraticiensH extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	
	private ModeleListePraticiensH modeleTableauPraticiensH ;
	private JTable tableauPraticiensH ;
	
	/** Créer la vue dédiée à l'affichage de la liste des Praticiens Hesitants
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public VueListePraticiensH(AccesModele modele, Controleur controleur) {
		super();
		System.out.println("VueListePraticiensH::VueListePraticiensH()") ;
		
		this.modele = modele ;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;

		boxEtiquette.add(new JLabel("Praticiens Hesitants :")) ;
		boxEtiquette.add(Box.createHorizontalGlue()) ;
		
		modeleTableauPraticiensH = new ModeleListePraticiensH(modele) ;
		tableauPraticiensH = new JTable(modeleTableauPraticiensH) ;
		tableauPraticiensH.setRowHeight(30) ;
		tableauPraticiensH.getTableHeader().setPreferredSize(new Dimension(0, 45));
		
		TableRowSorter<ModeleListePraticiensH> sorter = new TableRowSorter<ModeleListePraticiensH>(modeleTableauPraticiensH);   
		tableauPraticiensH.setRowSorter(sorter);
		
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		
		sorter.setComparator(2, new CoefComparator());
		sorter.setComparator(4, new CoefComparator());
		
		/*tableauPraticiensH. getTableHeader().addMouseListener(new MouseAdapter(){
			
			 public void mouseClicked(MouseEvent e) {
			        int colonne = tableauPraticiensH.columnAtPoint(e.getPoint());
			        String name = tableauPraticiensH.getColumnName(colonne);
			        System.out.println("Column index selected " + colonne + " " + name);
			    }

		});*/
	
				
				
		JScrollPane spPraticiens = new JScrollPane(tableauPraticiensH) ;
		
		spPraticiens.setPreferredSize(new Dimension(1090,420)) ;
		
		boxTableau.add(spPraticiens) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
		
	}


	/** Actualiser le modèle du tableau
	 * 
	 */
	public void actualiser(){
		System.out.println("VueListePraticiensH::actualiser()") ;
		modeleTableauPraticiensH = new ModeleListePraticiensH(modele) ;
		tableauPraticiensH.setModel(modeleTableauPraticiensH) ;
	}
	
}
