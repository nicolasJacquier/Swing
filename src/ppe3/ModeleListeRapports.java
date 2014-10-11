package ppe3;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class ModeleListeRapports extends AbstractTableModel {
	private List<RapportVisite> rapportsVisite ;
	private final String[] entetes = {"Nom Praticien","Ville","Date visite","Date Redaction","Bilan","Lu"} ;
	private String luT = "true" ;
	private AccesModele modele ;
	private Controleur controleur;
	
	/** Créer le modèle de la liste des rapports
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleListeRapports(AccesModele modele, Controleur controleur){
		super() ; 
		System.out.println("ModeleListeRapports::ModeleListeRapports()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		rapportsVisite = modele.getRapportsVisite() ;
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
		return controleur;
	}
	
	/** Obtenir le bilan du rapport de visite
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le bilan du rapport de visite 
	 */
	public String getBilanRapport(int indiceLigne){
		return rapportsVisite.get(indiceLigne).getBilan() ;
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	public int getRowCount(){
		//System.out.println("ModeleListeVehicules::getRowCount()") ;
		return rapportsVisite.size() ;
	}

	/** Obtenir le nombre de colonnes
	 * 
	 * @return Le nombre de colonnes
	 */
	public int getColumnCount(){
		//System.out.println("ModeleListeVehicules::getColumnCount()") ;
		return entetes.length ;
	}

	/** Obtenir le nom d'une colonne
	 * 
	 * @param indiceColonne L'indice de la colonne
	 * @return Le nom de la colonne
	 */
	public String getColumnName(int indiceColonne){
		//System.out.println("ModeleListeVehicules::getColumnName()") ;
		return entetes[indiceColonne] ;
	}
	
	/** Obtenir la classe d'une colonne
	 * 
	 * @param indiceColonne Le numéro de la colonne
	 * @return La classe de la colonne
	 */
	public Class getColumnClass(int indiceColonne){
		//System.out.println("ModeleListeLocations::getColumnClass()") ;
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
			case 5 :
				return String.class ;
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
		//System.out.println("ModeleListeVehicules::getValueAt()") ;
		switch(indiceColonne){
			case 0 : 
				return rapportsVisite.get(indiceLigne).getPraticienNom() ;
			case 1 : 
				return rapportsVisite.get(indiceLigne).getPraticienVille() ;
			case 2 :
				return Dates.parseDate(rapportsVisite.get(indiceLigne).getDateVisite()) ;
			case 3 : 
				return Dates.parseDate(rapportsVisite.get(indiceLigne).getDateRedac()) ;
			case 4 : 
				return "Contenu" ;
			case 5 :
				if (rapportsVisite.get(indiceLigne).getLu() == null){
					return "Non renseigné";
				}
				else{
					if(rapportsVisite.get(indiceLigne).getLu() == luT){
						return "Oui" ;
					}
					else {
						return "Non" ;
					}
				}
			default :
				return null ;
		}
	}

}
