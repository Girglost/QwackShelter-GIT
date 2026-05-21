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

	public Benevole(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron,LocalDate dateEngagement) {
		super(id, nom, prenom, login, password, habitation, patron);
		this.dateEngagement=dateEngagement;
	}	

	@Override
	public String toString() {
		return "Benevole [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + "]";
	}

	
}
