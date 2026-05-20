package quack.model;

public class Utilisateur extends Personne {
	
	public Utilisateur() {
	}
	public Utilisateur(Integer id, String nom, String prenom, Lieu habitation) {
		super(id, nom, prenom, habitation);
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", habitation=" + habitation + "]";
	}

}
