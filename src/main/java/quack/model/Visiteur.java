package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Visiteur")
public class Visiteur extends Personne {
	
	public Visiteur() {
	}

	public Visiteur(Integer id, String nom, String prenom, String login, String password, Lieu habitation) {
		super(id, nom, prenom, login, password, habitation);
	}

	@Override
	public String toString() {
		return "Visiteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + "]";
	}

}
