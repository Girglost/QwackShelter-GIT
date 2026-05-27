package quack.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="historique_sante")
public class HistoriqueSante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String commentaire;
	@Column(columnDefinition = "DECIMAL(6,2)", nullable=false)
	private double poids;
	@Column(nullable=false)
	private LocalDate date;
	@Column(nullable=false, name="time")
	private LocalTime heure;
	private int duree;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Cause cause;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Animal animal;
	
	
	
	public HistoriqueSante(String commentaire, double poids, LocalDate date, LocalTime heure, int duree, Cause cause, Animal animal) {
		this.commentaire = commentaire;
		this.poids = poids;
		this.date = date;
		this.heure = heure;
		this.duree = duree;
		this.cause = cause;
		this.animal = animal;
	}
	
	public HistoriqueSante(String commentaire, double poids, Cause cause, Animal animal) {
		this.commentaire = commentaire;
		this.poids = poids;
		this.date = LocalDate.now();
		this.heure = LocalTime.now();
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
