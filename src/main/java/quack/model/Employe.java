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
	
	private boolean responsable; // responsable = patron ;
	
	@Column(name="date_embauche")
	private LocalDate dateEmbauche;
	
	public Employe() {}

	public Employe(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron, double salaire, boolean responsable,LocalDate dateEmbauche) {
		super(id, nom, prenom, login, password, habitation, patron);
		this.salaire = salaire;
		this.responsable = responsable;
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

	public boolean isResponsable() {
		return responsable;
	}

	public void setResponsable(boolean responsable) {
		this.responsable = responsable;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", salaire=" + salaire + ", responsable=" + responsable
				+ ", dateEmbauche=" + dateEmbauche + "]";
	}

	
}
