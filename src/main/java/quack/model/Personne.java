package quack.model;

public abstract class Personne {

	protected Integer id;
	protected String nom;
	protected String prenom;




	//constructeur vide

	public Personne() {}

	//constructeur
	public Personne(Integer id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	//get set

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	//to String

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	

	//methodes

	public void demandeAdoption() {

	}

	public void demandeVisite() {

	}

	public void faireDon() {

	}

	public void parrainerAnimal() {

	}

	public void adopter(Animal animal) {

	}
}
