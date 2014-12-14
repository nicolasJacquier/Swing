package ppe3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class EcouteurBoutonRapports implements ActionListener {
	private int row ;
	private int column ;
	private JTable table ;
	private JButton bouton ;

	/** Modifier l'indice de la ligne
	 * @param row L'indice de la ligne
	 */
	public void setRow(int row){
		this.row = row ;
	}

	/** Modifier l'indice de la colonne
	 * @param column L'indice de la colonne
	 */
	public void setColumn(int column){
		this.column = column ;
	}

	/** Modifier le tableau
	 * @param table Le nouveau tableau
	 */
	public void setTable(JTable table){
		this.table = table ;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("EcouteurBoutonRapports::actionPerformed()") ;
		AccesModele modele = ((ModeleListeRapports) this.table.getModel()).getModele() ;
		Controleur controleur = ((ModeleListeRapports) this.table.getModel()).getControleur() ;
		String bilanRapport = ((ModeleListeRapports)this.table.getModel()).getBilanRapport(this.row) ;
		String numRapport = ((ModeleListeRapports)this.table.getModel()).getNumBilanRapport(this.row) ;

		switch(this.column){
		case 5 :
			System.out.println("----------------------------------------") ;
			System.out.println("[Lire bilan]") ;
//			JOptionPane.showMessageDialog(null, bilanRapport, "Bilan du rapport de visite", JOptionPane.PLAIN_MESSAGE);
			((ModeleListeRapports) this.table.getModel()).setValueAt(new String(), this.row, 6);
			controleur.setRapportVisiteLu(numRapport);
			controleur.setNumRapport(numRapport);
			modele.remplirRapport(numRapport);
			new ModeleDetailRapports(modele, controleur, numRapport) ;
			controleur.visualiserDetailRapport();

			break ;
		}			
	}

}
