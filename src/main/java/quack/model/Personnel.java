package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// On ne la persiste pas en BDD
public abstract class Personnel extends Personne{


	public Personnel() {}
	public Personnel(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron) {
		super(id, nom, prenom, login, password, habitation);
	}

	
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation+"]";
	}
}
