package qwack_boot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "visiteur", nullable = false)
	private Personne visiteur;

	@ManyToOne
	@JoinColumn(name = "animal", nullable = false)
	private Animal animal;

	@ManyToOne
	@JoinColumn(name = "quack_shelter", nullable = false)
	private QuackShelter quackShelter;

	@Column
	private LocalDateTime dateVisite;

	public Visite() {
	}

	public Visite(Personne personne, Animal animal, QuackShelter quackShelter, LocalDateTime dateVisite) {
		this.visiteur = personne;
		this.animal = animal;
		this.quackShelter = quackShelter;
		this.dateVisite = dateVisite;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Personne getVisiteur() {
		return visiteur;
	}

	public void setVisiteur(Personne visiteur) {
		this.visiteur = visiteur;
	}

	public QuackShelter getQuackShelter() {
		return quackShelter;
	}

	public void setQuackShelter(QuackShelter quackShelter) {
		this.quackShelter = quackShelter;
	}

	public LocalDateTime getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDateTime dateVisite) {
		this.dateVisite = dateVisite;
	}

	@Override
	public String toString() {
		return "Visite [id=" + id + ", visiteur=" + visiteur + ", animal=" + animal + ", quackShelter=" + quackShelter
				+ ", dateVisite=" + dateVisite + "]";
	}

}
