package quack.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Employe")
public class Employe extends Personnel {
	
	@Column(length=5)
	private double salaire;
	

	
	@Column(name="date_embauche")
	private LocalDate dateEmbauche;
	
	public Employe() {}

	public Employe(String nom, String prenom, String login, String password, Lieu habitation,
			boolean admin, double salaire,LocalDate dateEmbauche,QuackShelter quackshelter) {
		super(nom, prenom, login, password, habitation, admin,quackshelter);
		this.salaire = salaire;

		this.dateEmbauche = dateEmbauche;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}


	@Override
	public String toString() {
		return "Employe [admin=" + admin + ", id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login
				+ ", password=" + password + ", habitation=" + habitation + ", salaire=" + salaire + ", dateEmbauche="
				+ dateEmbauche + "]";
	}

	
}
