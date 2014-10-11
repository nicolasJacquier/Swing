package ppe3;

public class Praticien {
	private int numero ;
	private String nom ;
	private String prenom ;
	private String ville ;
	
	/** Créer un praticien
	 * 
	 * @param numero Le numéro du praticien
	 * @param nom Le nom
	 * @param prenom Le prénom
	 * @param mobile Le nom de la ville 
	 */
	public Praticien(int numero, String nom, String prenom, String ville) {
		super() ;
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
}
