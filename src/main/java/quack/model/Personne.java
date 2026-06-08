package quack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('Employe','Benevole','Patron','Visiteur')")
public abstract class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(length = 25,nullable=false)
	protected String nom;
	@Column(length = 25,nullable=false)
	protected String prenom;
	@Column(length = 25,nullable=false)
	protected String login;
	@Column(length = 25,nullable=false)
	protected String password;

	@ManyToOne
    @JoinColumn(name="habitation",nullable = false)
	protected Lieu habitation;
	
	@OneToMany(mappedBy="adoptant")
	protected List<StatutAnimal> adoptions = new ArrayList(); 
	
	@OneToMany(mappedBy="visiteur")
	protected List<Visite> visites = new ArrayList(); 

	//constructeur vide
	public Personne() {}
	public Personne(String nom, String prenom, String login, String password, Lieu habitation) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.habitation = habitation;
	}

	//get set

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Lieu getHabitation() {
		return habitation;
	}
	public void setHabitation(Lieu habitation) {
		this.habitation = habitation;
	}
	
	public List<StatutAnimal> getAdoptions() {
		return adoptions;
	}
	public void setAdoptions(List<StatutAnimal> adoptions) {
		this.adoptions = adoptions;
	}

	public List<Visite> getVisites() {
		return visites;
	}
	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}
	//to String

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + "]";
	}
	
}
