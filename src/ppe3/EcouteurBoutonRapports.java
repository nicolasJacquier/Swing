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
	public void actionPerformed(ActionEvent e) {
		System.out.println("EcouteurBoutonRapports::actionPerformed()") ;
		if(table.getModel().getClass() == ModeleListeRapports.class){
			Controleur controleur = ((ModeleListeRapports) this.table.getModel()).getControleur() ;
			String bilanRapport = ((ModeleListeRapports)this.table.getModel()).getBilanRapport(this.row) ;

			switch(this.column){
				case 4 :
					System.out.println("----------------------------------------") ;
					System.out.println("[Lire bilan]") ;
					JOptionPane.showMessageDialog(null, bilanRapport, "Bilan du rapport de visite", JOptionPane.PLAIN_MESSAGE);
					((ModeleListeRapports) this.table.getModel()).setValueAt(new String("Oui"), this.row, 5);
					break ;
				}
		}
		else{
			Controleur controleur = ((ModeleListeRapportsModifie) this.table.getModel()).getControleur() ;
			String bilanRapport = ((ModeleListeRapportsModifie)this.table.getModel()).getBilanRapport(this.row) ;

			switch(this.column){
				case 4 :
					System.out.println("----------------------------------------") ;
					System.out.println("[Lire bilan]") ;
					JOptionPane.showMessageDialog(null, bilanRapport, "Bilan du rapport de visite", JOptionPane.PLAIN_MESSAGE);
					((ModeleListeRapportsModifie) this.table.getModel()).setValueAt(new String("Oui"), this.row, 5);
					break ;
				}
		}
		
	}
	
}
