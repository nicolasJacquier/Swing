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

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

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
		String matVisiteur = ((ModeleListeVisiteurs) this.table.getModel()).getMatVisiteur(this.row) ;
		
		switch(this.column) {
		case 4 :
			System.out.println("----------------------------------------") ;
			System.out.println("[Sélectionner visiteur]") ;
			
			JMonthChooser chooserM = new JMonthChooser();
			JYearChooser chooserY = new JYearChooser();
			JLabel lMois = new JLabel("Mois");
			JLabel lAnnee = new JLabel("Année");
			lMois.setLabelFor(chooserM);
			lAnnee.setLabelFor(chooserY);
			
			
			String Message = "Choisir le mois et l'année \n du rapport de visite du visiteur " + nomVisiteur + ".";
			Object[] content = {Message,lMois,chooserM,lAnnee,chooserY};
			int dialogBox = JOptionPane.showOptionDialog(null,content, "Choix",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, null, null);
			if (dialogBox == JOptionPane.OK_OPTION){
				try {
					chooserM.setMonth(chooserM.getMonth() + 1);
					controleur.creerRapportVisite(matVisiteur, chooserY.getYear(), chooserM.getMonth());
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
