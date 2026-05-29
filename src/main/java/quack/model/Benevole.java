package quack.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Benevole")
public class Benevole extends Personnel {
	
	@Column(name="date_engagement")
	private LocalDate dateEngagement;
	public Benevole() {}

	public Benevole(String nom, String prenom, String login, String password, Lieu habitation,
			boolean admin,LocalDate dateEngagement,QuackShelter quackshelter) {
		super(nom, prenom, login, password, habitation, admin,quackshelter);
		this.dateEngagement=dateEngagement;
	}	

	@Override
	public String toString() {
		return "Benevole [admin=" + admin + ", id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login
				+ ", password=" + password + ", habitation=" + habitation + ", dateEngagement=" + dateEngagement + "]";
	}

	
}
