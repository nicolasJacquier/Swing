package ppe3;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class ModeleListeRapports extends AbstractTableModel {
	private List<RapportVisite> rapportsVisite = new ArrayList<RapportVisite>() ;
	private final String[] entetes = {"Visiteur","Nom Praticien","Ville","Date visite","Date Redaction","Bilan","Lu"} ;
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
		System.out.println("ModeleListeRapports::getControleur()");
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
	
	/** Obtenir le numéro du rapport de visite
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le numéro du rapport de visite 
	 */
	public String getNumBilanRapport(int indiceLigne){
		return rapportsVisite.get(indiceLigne).getRapNum() ;
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
	
	/** Savoir si un bilan de rapport de visite a été lu
	 * 
	 * @param value L'objet selectionné à modifier
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 */
	public void setValueAt(Object value, int indiceLigne, int indiceColonne) {
		if(indiceColonne == 6){
			rapportsVisite.get(indiceLigne).setLu("Oui");
			//controleur.setRapportVisiteLu(rapportsVisite.get(indiceLigne).getRapNum());
//			setRead et unsetRead lors de la fermeture de l'application
		}
		fireTableCellUpdated(indiceLigne, indiceColonne); 
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
				return String.class ;
			case 5 :
				return JButton.class ;
			case 6 :
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
				return rapportsVisite.get(indiceLigne).getVisiteurNom()+" ("+rapportsVisite.get(indiceLigne).getVisiteurMat()+")" ;
			case 1 : 
				return rapportsVisite.get(indiceLigne).getPraticienNom() ;
			case 2 : 
				return rapportsVisite.get(indiceLigne).getPraticienVille() ;
			case 3 :
				return Dates.parseDate(rapportsVisite.get(indiceLigne).getDateVisite()) ;
			case 4 : 
				return Dates.parseDate(rapportsVisite.get(indiceLigne).getDateRedac()) ;
			case 5 : 
				return "Contenu" ;
			case 6 :
				if (rapportsVisite.get(indiceLigne).getLu() == null){
					return "Non renseigné";
				}
				else{
					if(rapportsVisite.get(indiceLigne).getLu() == "Oui"){
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
                	  return false;
                  case 5: 
                	  return ( true );
                  case 6: 
                	  return false;
                  default:
                	  return false;
             }
         }
	
}
