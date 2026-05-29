package quack.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Personnel")
public abstract class Personnel extends Personne{

	@Column
    protected boolean admin;
    
	public Personnel() {}
	public Personnel(String nom, String prenom, String login, String password, Lieu habitation,
			boolean admin) {
		super(nom, prenom, login, password, habitation);
		this.admin = admin;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", admin=" + admin + "]";
	}
	
	
}
