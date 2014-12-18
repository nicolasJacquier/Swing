package ppe3;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeleDetailRapports extends AbstractTableModel {

	private List<RapportVisite> rapportsVisite = new ArrayList<RapportVisite>() ;
	private List<RapportVisite> rapportsVisiteSelec = new ArrayList<RapportVisite>() ;
	private final String[] entetes = {"Numéro du rapport","Nom du Visiteur","Nom du praticien","Date de visite","Date de rédaction","Bilan"} ;
	private AccesModele modele ;
	private Controleur controleur;
	private RapportVisite rapportSelec ;
	private String matRap;


	/** Créer le modèle de la liste des rapports
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleDetailRapports(AccesModele modele, Controleur controleur, String matRap){
		super() ; 
		System.out.println("ModeleListeRapports::ModeleListeRapports()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		this.matRap = matRap ;
//		matNumRap = Integer.parseInt(matRap) ;
//		System.out.println(matNumRap);
//		System.out.println(rapportsVisite.size());
		matRap = controleur.getNumRapport() ;
		System.out.println(matRap);
		for(RapportVisite rapportVisite : modele.getRapportsVisite()){
			System.out.println(rapportVisite.getRapNum());
			System.out.println(modele.getRapportsVisite());
//			this.rapportsVisiteSelec.add(rapportVisite) ;
//			if(rapportsVisiteSelec.size() == 1){
//				this.rapportsVisiteSelec.remove(0);
//			}		
			rapportSelec = null ;
			rapportSelec = modele.getRapportsVisite().get(modele.getRapportsVisite().size()-1);
		}
		
		System.out.println(rapportsVisiteSelec.size());
		// Mettre méthode pour récupérer LE rapport.
	}
	
	public ModeleDetailRapports(AccesModele modele, Controleur controleur){
		super() ; 
		System.out.println("ModeleListeRapports::ModeleListeRapports()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		matRap = controleur.getNumRapport() ;
//		System.out.println(matRap);
		for(RapportVisite rapportVisite : modele.getRapportsVisite()){
//			System.out.println(rapportVisite.getRapNum());
//			System.out.println(modele.getRapportsVisite());
			rapportSelec = null ;
			rapportSelec = modele.getRapportsVisite().get(modele.getRapportsVisite().size()-1);
//			if(rapportsVisiteSelec.size() == 1){
//				this.rapportsVisiteSelec.remove(0);
//			}				
		}
		
		System.out.println(rapportsVisiteSelec.size());
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	public int getRowCount(){
		return 1;
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

	@Override
	public Object getValueAt(int indiceLigne, int indiceColonne) {
		switch(indiceColonne){
		case 0 : 
			return rapportSelec.getRapNum() ;
		case 1 :
			return rapportSelec.getVisiteurNom() ;
		case 2 :
			return rapportSelec.getPraticienNom() ;
		case 3 :
			return Dates.parseDate(rapportSelec.getDateVisite()) ;
		case 4 :
			return Dates.parseDate(rapportSelec.getDateRedac()) ;
		case 5 :
			return rapportSelec.getBilan() ;
		default :
			return null ;
		}
	}
}
