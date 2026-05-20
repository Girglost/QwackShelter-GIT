package quack.model;

public class Admin extends Personne {
	
	
	public Admin() {
	}
	public Admin(Integer id, String nom, String prenom, Lieu habitation) {
		super(id, nom, prenom, habitation);
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", habitation=" + habitation + "]";
	}


}
