package quack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("Admin")
public class Admin extends Personne {
	

	public Admin() {
	}
	public Admin(Integer id, String nom, String prenom, String login, String password, Lieu habitation) {
		super(id, nom, prenom,login,password, habitation);
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password
				+ ", habitation=" + habitation + "]";
	}



}
