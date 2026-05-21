package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Benevole")
public class Benevole extends Personnel {

	public Benevole() {}

	public Benevole(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron) {
		super(id, nom, prenom, login, password, habitation, patron);
	}

	@Override
	public String toString() {
		return "Benevole [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + "]";
	}

	
}
