package quack.model;

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
	@JoinColumn(name="visiteur",nullable = false)
	private Personne personne;
	
	@ManyToOne
	@JoinColumn(name="quackshelter",nullable = false)
	private QuackShelter quackshelter;
	
	@Column
	private LocalDateTime dateVisite;

	public Visite() {
	}
	public Visite(Personne personne, QuackShelter quackshelter, LocalDateTime dateVisite) {
		this.personne = personne;
		this.quackshelter = quackshelter;
		this.dateVisite = dateVisite;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public QuackShelter getQuackshelter() {
		return quackshelter;
	}
	public void setQuackshelter(QuackShelter quackshelter) {
		this.quackshelter = quackshelter;
	}
	public LocalDateTime getDateVisite() {
		return dateVisite;
	}
	public void setDateVisite(LocalDateTime dateVisite) {
		this.dateVisite = dateVisite;
	}
	@Override
	public String toString() {
		return "Visite [id=" + id + ", personne=" + personne + ", quackshelter=" + quackshelter + ", dateVisite="
				+ dateVisite + "]";
	}
	
	
	
	
	
}
