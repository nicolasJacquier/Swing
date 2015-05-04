package ppe3;

public class AppliCR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("AppliCR::main()") ;
		
		System.out.println(" [Création du modèle]") ;
		AccesModele modele = new AccesModele() ;
		
		System.out.println(" [Création du contrôleur]") ;
		Controleur controleur = new Controleur(modele) ;
		
		System.out.println(" [Création de la vue principale]") ;
		new GuiAppliCR(modele,controleur) ;
	}

}