package ppe3;

public class Visiteur {
	private String numero ;
	private String nom ;
	private String prenom ;
	private String adresse ;
	
	/** Créer un visiteur
	 * 
	 * @param numero Le numéro du visiteur
	 * @param nom Le nom
	 * @param prenom Le prénom
	 * @param adresse L'adresse du visiteur
	 */
	public Visiteur(String numero, String nom, String prenom, String adresse) {
		super();
		//System.out.println("Visiteur::Visiteur(int,String,String,String)") ;
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Visiteur [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", adresse=" + adresse + "]";
	}

}
