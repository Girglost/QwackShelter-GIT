package quack.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistoriqueSante {

	private Integer id;
	private String commentaire;
	private double poids;
	private LocalDate date;
	private LocalTime heure;
	private int duree;
	private Cause cause;
	private Animal animal;
	
	
	
	public HistoriqueSante(Integer id, String commentaire, double poids, LocalDate date, LocalTime heure, int duree, Cause cause, Animal animal) {
		this.id = id;
		this.commentaire = commentaire;
		this.poids = poids;
		this.date = date;
		this.heure = heure;
		this.duree = duree;
		this.cause = cause;
		this.animal = animal;
	}

	public HistoriqueSante() {}

	
	
	
	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHeure() {
		return heure;
	}

	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "HistoriqueSante [id=" + id + ", commentaire=" + commentaire + ", poids=" + poids + ", date=" + date
				+ ", heure=" + heure + ", duree=" + duree + ", cause=" + cause + ", animal=" + animal + "]";
	}

		
	
}
