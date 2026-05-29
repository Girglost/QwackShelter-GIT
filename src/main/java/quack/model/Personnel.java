package quack.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Personnel")
public abstract class Personnel extends Personne{

	@Column
    protected boolean admin;
	
	

	@ManyToOne
    @JoinColumn(name = "quack_shelter_id")
    private QuackShelter quackShelter;
    
	public Personnel() {}
	public Personnel(String nom, String prenom, String login, String password, Lieu habitation,
			boolean admin,QuackShelter quackShelter) {
		super(nom, prenom, login, password, habitation);
		this.admin = admin;
		this.quackShelter = quackShelter;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public QuackShelter getQuackShelter() {
		return quackShelter;
	}
	public void setQuackShelter(QuackShelter quackShelter) {
		this.quackShelter = quackShelter;
	}
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", admin=" + admin + "]";
	}
}
