package qwack_boot.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "statut_animal")
public class StatutAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, name = "date_arrivee")
	private LocalDate dateArrivee;
	@Column(name = "date_depart")
	private LocalDate dateDepart;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Statut statut;
	@ManyToOne
	@JoinColumn(name = "emplacement")
	private Emplacement emplacement;
	@Enumerated(EnumType.STRING)
	@Column(name="statut_adoption")
	private StatutValidation statutAdoption;
	@ManyToOne
	@JoinColumn(name = "adoptant", nullable = true)
	private Personne adoptant;
	@OneToOne
	@JoinColumn(nullable = false, name = "animal")
	private Animal animal;

	public StatutAnimal() {
	}

	public StatutAnimal(Emplacement emplacement, Animal animal){
		this.dateArrivee = LocalDate.now();
		this.statut = Statut.Present;
		this.emplacement = emplacement;
		this.animal = animal;
	}


	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public StatutValidation getStatutAdoption() {
		return statutAdoption;
	}

	public void setStatutAdoption(StatutValidation statutAdoption) {
		this.statutAdoption = statutAdoption;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Emplacement getEmplacement() {
		return emplacement;
	}

	public Personne getAdoptant() {
		return adoptant;
	}

	public void setAdoptant(Personne adoptant) {
		this.adoptant = adoptant;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return "StatuAnimalRefuge [id=" + id + ", dateArrivee=" + dateArrivee + ", dateDepart=" + dateDepart
				+ ", statut=" + statut + ", emplacement=" + emplacement + ", adoptant=" + adoptant + "]";
	}

}
