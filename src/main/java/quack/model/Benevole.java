package quack.model;

public class Benevole extends Personnel {

	public Benevole() {}
	public Benevole(Integer id, String nom, String prenom, boolean patron, Lieu habitation) {
		super(id, nom, prenom, patron, habitation);
	}

	@Override
	public String toString() {
		return "Benevole [patron=" + patron + ", id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", habitation="
				+ habitation + "]";
	}
	
}
