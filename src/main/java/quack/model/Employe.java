package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Employe")
public class Employe extends Personnel {

	private double salaire;
	private boolean responsable; // responsable = patron ;
	
	public Employe() {}

	public Employe(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron, double salaire, boolean responsable) {
		super(id, nom, prenom, login, password, habitation, patron);
		this.salaire = salaire;
		this.responsable = responsable;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public boolean isResponsable() {
		return responsable;
	}

	public void setResponsable(boolean responsable) {
		this.responsable = responsable;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", salaire=" + salaire + ", responsable=" + responsable
				+ "]";
	}

	
}
