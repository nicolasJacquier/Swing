package ppe3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EcouteurBoutonVisiteurs implements ActionListener {
	private int row ;
	private int column ;
	private JTable table ;
	private JButton bouton ;
	private JTextField visiteurField ;

	/** Modifier l'indice de la ligne
	 * @param row L'indice de la ligne
	 */
	public void setRow(int row){
		this.row = row ;
	}

	/** Modifier l'indice de la colonne
	 * 
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
		System.out.println("EcouteurBoutonVisiteurs::actionPerformed()") ;
		Controleur controleur = ((ModeleListeVisiteurs) this.table.getModel()).getControleur() ;
		AccesModele modele = ((ModeleListeVisiteurs) this.table.getModel()).getModele() ;
		String nomVisiteur = ((ModeleListeVisiteurs) this.table.getModel()).getNomVisiteur(this.row) ;
		
		switch(this.column) {
		case 4 :
			System.out.println("----------------------------------------") ;
			System.out.println("[Sélectionner visiteur]") ;
			
			JLabel labelMois = new JLabel("Mois :");
			JTextField moisField = new JTextField();
			JLabel labelAnnee = new JLabel("Année :");
			JTextField anneeField = new JTextField();
			String[] moisList = {"Tous les mois", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			JComboBox moisBox = new JComboBox(moisList) ;
			String[] anneeList = {"Toutes les années", "2010", "2011", "2012", "2013", "2014" };
			JComboBox anneeBox = new JComboBox(anneeList) ;
			labelMois.setLabelFor(moisBox);
			labelAnnee.setLabelFor(anneeBox);
			String Message = "Choisir le mois et l'année \n du rapport de visite du visiteur ";
			Object[] content = {Message,labelMois,moisBox,labelAnnee,anneeBox};
			int dialogBox = JOptionPane.showOptionDialog(null,content, "Choix",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, null, null);
			if (dialogBox == JOptionPane.OK_OPTION){
				try {
//					String moisText = moisField.getText() ;
//					String anneeText = anneeField.getText() ;
					String moisText = moisBox.getSelectedItem().toString();
					String anneeText = anneeBox.getSelectedItem().toString();
					((ModeleListeVisiteurs) this.table.getModel()).getControleur().setVisiteurRapport(nomVisiteur);
					((ModeleListeVisiteurs) this.table.getModel()).getControleur().setMoisRapport(moisText);
					((ModeleListeVisiteurs) this.table.getModel()).getControleur().setAnneeRapport(anneeText);
//					JOptionPane.showMessageDialog(null, "Rapport de visite de "+nomVisiteur+" selectionné.", "Sélection", JOptionPane.PLAIN_MESSAGE);
					controleur.visualiserRapportsVisite();
				}
				catch(NullPointerException npe) {
					npe.getMessage();
				}
			}
			break ;
		}
	}	

}
