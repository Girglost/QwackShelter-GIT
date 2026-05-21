package quack.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Visiteur")
public class Visiteur extends Personne {
	
	@Column(name="date_inscription")
	private LocalDate dateInscription;
	
	/*

	 @OneToMany(mappedBy=Visite);
	 private List<Visite> historiqueVisites;
	 
	 @OneToMany(mappedBy=Adoption)
	 private List<Adoption> historiqueAdoption; 
	 */
	
	
	public Visiteur() {
	}

	public Visiteur(Integer id, String nom, String prenom, String login, String password, Lieu habitation,LocalDate dateInscription) {
		super(id, nom, prenom, login, password, habitation);
		this.dateInscription= dateInscription;
	}
	
	
	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	@Override
	public String toString() {
		return "Visiteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", dateInscription=" + dateInscription + "]";
	}

}
