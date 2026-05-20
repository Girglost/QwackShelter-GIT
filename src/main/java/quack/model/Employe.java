package quack.model;

public class Employe extends Personnel {

	private double salaire;
	
	public Employe() {}
	public Employe(Integer id, String nom, String prenom, boolean patron, double salaire, Lieu habitation) {
		super(id, nom, prenom, patron, habitation);
		this.salaire = salaire;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		return "Employe [patron=" + patron + ", id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", habitation="
				+ habitation + ", salaire=" + salaire + "]";
	}
	
}
