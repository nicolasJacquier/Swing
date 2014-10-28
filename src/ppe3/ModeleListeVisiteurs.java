package ppe3 ;

import java.util.* ;
import javax.swing.table.* ;

/** Modèle du tableau des clients
 * 
 * @author xilim
 *
 */
public class ModeleListeVisiteurs extends AbstractTableModel {
	
	private List<Visiteur> visiteurs ;
	private final String[] entetes = {"Numéro","Nom","Prénom","Adresse"} ;
	
	private AccesModele modele ;
	
	/** Créer le modèle de la liste des clients
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleListeVisiteurs(AccesModele modele){
		super() ; 
		System.out.println("ModeleListeVisiteurs::ModeleListeVisiteurs()") ;
		this.modele = modele ;
		visiteurs = modele.getVisiteurs() ;
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
				return visiteurs.get(indiceLigne).getNumero() ;
			case 1 : 
				return visiteurs.get(indiceLigne).getNom() ;
			case 2 :
				return visiteurs.get(indiceLigne).getPrenom() ;
			case 3 : 
				return visiteurs.get(indiceLigne).getAdresse() ;
			default :
				return null ;
		}
	}
}
