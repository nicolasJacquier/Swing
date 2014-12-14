package ppe3 ;

import java.util.* ;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.* ;

/** Modèle du tableau des clients
 * 
 * @author xilim
 *
 */
public class ModeleListeVisiteurs extends AbstractTableModel {
	
	private List<Visiteur> visiteurs ;
	private final String[] entetes = {"Nom","Prénom","Adresse","Code Postal","Rapports"} ;
	
	private AccesModele modele ;
	private Controleur controleur;
	private VueListeRapports vueListeRapports;
	
	
	/** Créer le modèle de la liste des clients
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleListeVisiteurs(AccesModele modele, Controleur controleur){
		super() ; 
		System.out.println("ModeleListeVisiteurs::ModeleListeVisiteurs()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		visiteurs = modele.getVisiteurs() ;
	}
	
	/** Obtenir le modèle de l'application
	 * 
	 * @return Le modèle de l'application
	 */
	public AccesModele getModele() {
		return modele;
	}
	
	/** Obtenir le contrôleur
	 * 
	 * @return Le contrôleur
	 */
	public Controleur getControleur() {
		System.out.println("ModeleListeVisiteurs::getControleur()");
		return controleur;
	}
	
	/** Obtenir le nom du visiteur
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le nom du visiteur
	 */
	public String getNomVisiteur(int indiceLigne) {
		return visiteurs.get(indiceLigne).getNom() ;
	}
	
	/** Obtenir le numéro du visiteur
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le numéro du visiteur
	 */
	public String getMatVisiteur(int indiceLigne) {
		return visiteurs.get(indiceLigne).getNumero() ;
	}
	
	public VueListeRapports getVueListeRapports() {
		return vueListeRapports;
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	public int getRowCount(){
		//System.out.println("ModeleListeClients::getRowCount()") ;
		return visiteurs.size() ;
	}

	/** Obtenir le nombre de colonnes
	 * 
	 * @return Le nombre de colonnes
	 */
	public int getColumnCount(){
		//System.out.println("ModeleListeClients::getColumnCount()") ;
		return entetes.length ;
	}
	
	/** Obtenir le nom d'une colonne
	 * 
	 * @param indiceColonne L'indice de la colonne
	 * @return Le nom de la colonne
	 */
	public String getColumnName(int indiceColonne){
		//System.out.println("ModeleListeClients::getColumnName()") ;
		return entetes[indiceColonne] ;
	}
	
	/** Obtenir la classe d'une colonne
	 * 
	 * @param indiceColonne Le numéro de la colonne
	 * @return La classe de la colonne
	 */
	public Class getColumnClass(int indiceColonne){
		//System.out.println("ModeleListeRapports::getColumnClass()") ;
		switch(indiceColonne){
			case 0 :
				return String.class ;
			case 1 :
				return String.class ;
			case 2 : 
				return String.class ;
			case 3 :
				return String.class ;
			case 4 :
				return JButton.class ;
			default :
				return Object.class ;
		}
	}
	
	/** Obtenir la valeur d'une cellule
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 * @return La valeur de la cellule
	 */
	public Object getValueAt(int indiceLigne, int indiceColonne){
		//System.out.println("ModeleListeClients::getValueAt()") ;
		switch(indiceColonne){
			case 0 : 
				return visiteurs.get(indiceLigne).getNom() ;
			case 1 : 
				return visiteurs.get(indiceLigne).getPrenom() ;
			case 2 :
				return visiteurs.get(indiceLigne).getAdresse() ;
			case 3 : 
				return visiteurs.get(indiceLigne).getNumCp() ;
			case 4 :
				return "Sélectionner" ;
			default :
				return null ;
		}
	}
	
	/** Permet de vérifier si une cellule est éditable
	 * @return La valeur de la cellule
	 */
	public boolean isCellEditable(int indiceLigne, int indiceColonne){
		switch(indiceColonne){
                  case 0: 
                	  return false;
                  case 1: 
                	  return false;
                  case 2: 
                	  return false;
                  case 3: 
                	  return false;
                  case 4: 
                	  return ( true );
                  default:
                	  return false;
             }
         }
}
