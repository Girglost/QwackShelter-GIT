package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("Patron")
public class Patron extends Personne {
	

	public Patron() {
	}
	public Patron(String nom, String prenom, String login, String password, Lieu habitation) {
		super(nom, prenom,login,password, habitation);
	}
	
	@Override
	public String toString() {
		return "Patron [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password
				+ ", habitation=" + habitation + "]";
	}



}
