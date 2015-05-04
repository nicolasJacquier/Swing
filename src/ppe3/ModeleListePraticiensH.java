package ppe3 ;

import java.util.* ;

import javax.swing.table.* ;

/** Modèle du tableau des Praticiens Hesitants
 * 
 * @author xilim
 *
 */
public class ModeleListePraticiensH extends AbstractTableModel {
	
	private List<PraticienH> PraticiensH = new ArrayList<PraticienH>() ;
	private final String[] entetes = {"Nom","Ville","Coeff confiance","Temps Ecoule","Coeff Notoriete"} ;
	
	private AccesModele modele ;
	
	/** Créer le modèle de la liste des Praticiens
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleListePraticiensH(AccesModele modele){
		super() ; 
		System.out.println("ModeleListePraticiens::ModeleListePraticiens()") ;
		this.modele = modele ;
		PraticiensH = modele.getPraticiensH() ;
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	public int getRowCount(){
		
		return PraticiensH.size() ;
	}

	/** Obtenir le nombre de colonnes
	 * 
	 * @return Le nombre de colonnes
	 */
	public int getColumnCount(){
		
		return entetes.length ;
	}
	
	/** Obtenir le nom d'une colonne
	 * 
	 * @param indiceColonne L'indice de la colonne
	 * @return Le nom de la colonne
	 */
	public String getColumnName(int indiceColonne){
		
		return entetes[indiceColonne] ;
	}
	
	/** Obtenir la valeur d'une cellule
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 * @return La valeur de la cellule
	 */
	public Object getValueAt(int indiceLigne, int indiceColonne){
	
		switch(indiceColonne){
			case 0 : 
				return PraticiensH.get(indiceLigne).getNom();
			case 1 : 
				return PraticiensH.get(indiceLigne).getVille();
			case 2 :
				return PraticiensH.get(indiceLigne).getCoefC();
			case 3 : 
				return PraticiensH.get(indiceLigne).getTempsEcoule()+"  jours" ;
			case 4 :
				return PraticiensH.get(indiceLigne).getCoefN();
			default :
				return null ;
		}
	}
	
}
