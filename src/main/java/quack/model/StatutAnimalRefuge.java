package quack.model;

import java.time.LocalDate;

public class StatutAnimalRefuge {

	private Integer id;
	private LocalDate dateArrivee;
	private LocalDate dateDepart;
	private Statut statut;
	private Emplacement emplecement;
	private Personne adoptant;
	private Animal animal;
	
	public StatutAnimalRefuge(LocalDate dateArrivee, LocalDate dateDepart, Statut statut, Emplacement emplecement, Personne adoptant, Animal animal) {
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.statut = statut;
		this.emplecement = emplecement;
		this.adoptant = adoptant;
		this.animal = animal;
	}
	
	public StatutAnimalRefuge(LocalDate dateArrivee, Statut statut, Emplacement emplecement,	Personne adoptant, Animal animal) {
		this.dateArrivee = dateArrivee;
		this.statut = statut;
		this.emplecement = emplecement;
		this.adoptant = adoptant;
		this.animal = animal;
	}
	
	public StatutAnimalRefuge(Statut statut, Emplacement emplecement, Personne adoptant, Animal animal) {
		this.dateArrivee = LocalDate.now();
		this.statut = statut;
		this.emplecement = emplecement;
		this.adoptant = adoptant;
		this.animal = animal;
	}
	
	public StatutAnimalRefuge() {}

	
	
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

	public Emplacement getEmplecement() {
		return emplecement;
	}

	public void setEmplecement(Emplacement emplecement) {
		this.emplecement = emplecement;
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
				+ ", statut=" + statut + ", emplecement=" + emplecement + ", adoptant=" + adoptant + ", animal="
				+ animal + "]";
	}

	
	
}
