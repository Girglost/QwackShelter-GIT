package quack.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_animal")
public abstract class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name="nom_animal",length = 50,nullable=true)
	protected String nomAnimal;
	@Column(name="date_naissance",nullable=false)
	protected LocalDate dateNaissance;
	@Column(length = 50, nullable = true)
	protected String couleur;
	@Column(name="regime_alimentaire",length = 200,nullable=true)
	protected String regimeAlimentaire;
	@Column(length = 200,nullable=true)
	protected String traitement;
	@Enumerated(EnumType.STRING)
	protected Famille famille;
	@Enumerated(EnumType.STRING)
	protected Genre genre;
	
  	@ElementCollection(targetClass = Caractere.class)
  	@Enumerated(EnumType.STRING)
  	@CollectionTable(name = "caracteres", joinColumns = @JoinColumn(name = "id_animal"))
	protected List<Caractere> caracteres = new ArrayList<>();
  	
  	@ManyToOne
  	@JoinColumn(name="refuge",nullable = false)
	protected QuackShelter quackShelter;
  	@OneToMany(mappedBy = "animal",cascade = CascadeType.ALL)
	protected List<HistoriqueSante> historiqueSante = new ArrayList<>();
  	@OneToOne(mappedBy = "animal",cascade = CascadeType.ALL)
  	protected StatutAnimal statutAnimal;
  	
	@OneToMany(mappedBy="animal")
	private List<Visite> visites = new ArrayList(); 
	
	public Animal( String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter quackShelter) {
		this.nomAnimal = nomAnimal;
		this.dateNaissance = dateNaissance;
		this.couleur = couleur;
		this.regimeAlimentaire = regimeAlimentaire;
		this.traitement = traitement;
		this.famille = famille;
		this.genre = genre;
		this.caracteres = caracteres;
		this.quackShelter = quackShelter;
	}
	
	public Animal( String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre,  QuackShelter quackShelter) {
		this.nomAnimal = nomAnimal;
		this.dateNaissance = dateNaissance;
		this.couleur = couleur;
		this.regimeAlimentaire = regimeAlimentaire;
		this.traitement = traitement;
		this.famille = famille;
		this.genre = genre;
		this.quackShelter = quackShelter;
	}

	public Animal() {
	}

	public int getId() {
		return id;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getCouleur() {
		return couleur;
	}

	public String getRegimeAlimentaire() {
		return regimeAlimentaire;
	}

	public String getTraitement() {
		return traitement;
	}

	public Famille getFamille() {
		return famille;
	}

	public Genre getGenre() {
		return genre;
	}

	public List<Caractere> getCaracteres() {
		return caracteres;
	}

	public QuackShelter getQuackShelter() {
		return quackShelter;
	}

	public List<HistoriqueSante> getHistoriqueSante() {
		return historiqueSante;
	}

	public StatutAnimal getStatutAnimal() {
		return statutAnimal;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public void setRegimeAlimentaire(String regimeAlimentaire) {
		this.regimeAlimentaire = regimeAlimentaire;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setCaracteres(List<Caractere> caracteres) {
		this.caracteres = caracteres;
	}

	public void setQuackShelter(QuackShelter quackShelter) {
		this.quackShelter = quackShelter;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public void setHistoriqueSante(List<HistoriqueSante> historiqueSante) {
		this.historiqueSante = historiqueSante;
	}

	public void setStatutAnimal(StatutAnimal statutAnimal) {
		this.statutAnimal = statutAnimal;
	}
	
	//Méthodrs
	/*
	public StatutAnimal showStatut() {
		
		for(StatutAnimal s: statutsAnimal) {
			if (s.getDateDepart()==null){
				return s;
			}
		}		
		return null;
	}*/

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", quackShelter=" + quackShelter
				+ "]";
	}
	
	
	
	
}
