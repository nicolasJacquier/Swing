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
		//System.out.println("EcouteurBoutonLocation::setRow()") ;
		this.row = row ;
	}

	/** Modifier l'indice de la colonne
	 * 
	 * @param column L'indice de la colonne
	 */
	public void setColumn(int column){
		//System.out.println("EcouteurBoutonLocation::setColumn()") ;
		this.column = column ;
	}

	/** Modifier le tableau
	 * @param table Le nouveau tableau
	 */
	public void setTable(JTable table){
		//System.out.println("EcouteurBoutonLocation::setTable()") ;
		this.table = table ;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("EcouteurBoutonRapports::actionPerformed()") ;
		Controleur controleur = ((ModeleListeRapports)this.table.getModel()).getControleur() ;
		//int numeroLocation = ((ModeleListeRapports)this.table.getModel()).getNumeroLocation(this.row) ;
		String bilanRapport = ((ModeleListeRapports)this.table.getModel()).getBilanRapport(this.row) ;

		switch(this.column){
			case 4 :
				System.out.println("----------------------------------------") ;
				System.out.println("[Lire bilan]") ;
				JOptionPane.showMessageDialog(null, bilanRapport, "Bilan du rapport de visite", JOptionPane.PLAIN_MESSAGE);
				break ;
			}
	}
}
