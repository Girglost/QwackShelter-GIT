package qwack_boot.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "login" }))
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 25, nullable = false)
	private String nom;
	@Column(length = 25, nullable = false)
	private String prenom;
	@Column(length = 100, nullable = false)
	private String login;
	@Column(length = 100, nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "habitation", nullable = false)
	private Lieu habitation;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column
	private boolean admin;

	@ManyToOne
	@JoinColumn(name = "quack_shelter_id")
	private QuackShelter quackShelter;

	// Concerne un visiteur
	@Column(name = "date_inscription")
	private LocalDate dateInscription;

	// Concerne un benevole
	@Column(name = "date_engagement")
	private LocalDate dateEngagement;

	// Concerne un employé ( un patron?)
	@Column(length = 5)
	private double salaire;

	@Column(name = "date_embauche")
	private LocalDate dateEmbauche;

	@OneToMany(mappedBy = "adoptant")
	private List<StatutAnimal> adoptions = new ArrayList();

	@OneToMany(mappedBy = "visiteur")
	private List<Visite> visites = new ArrayList();

	// Constructeur vide pour JPA
	public Personne() {
	}
	// Constructeur de BASE

	public Personne(String nom, String prenom, String login, String password, Lieu habitation,
			QuackShelter quackShelter) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.habitation = habitation;
		this.quackShelter = quackShelter;
	}

	// SEPARER LES ROLES quand on instancie

	public static Personne createVisiteur(String nom, String prenom, String login, String password, Lieu habitation,
			QuackShelter quackShelter) {
		Personne visiteur = new Personne(nom, prenom, login, password, habitation, quackShelter);

		visiteur.setDateInscription(LocalDate.now());
		visiteur.setRole(Role.VISITEUR);

		return visiteur;
	}

	public static Personne createBenevole(String nom, String prenom, String login, String password, Lieu habitation,
			QuackShelter quackShelter) {
		Personne benevole = new Personne(nom, prenom, login, password, habitation, quackShelter);

		benevole.setDateEngagement(LocalDate.now());
		benevole.setRole(Role.BENEVOLE);

		return benevole;
	}

	public static Personne createPatron(String nom, String prenom, String login, String password, Lieu habitation,
			QuackShelter quackShelter) {
		Personne patron = new Personne(nom, prenom, login, password, habitation, quackShelter);

		patron.setDateEmbauche(LocalDate.now());
		patron.setRole(Role.PATRON);
		patron.setAdmin(true);

		return patron;
	}

	public static Personne createEmploye(String nom, String prenom, String login, String password, Lieu habitation,
			QuackShelter quackShelter, boolean admin, double salaire) {
		Personne employe = new Personne(nom, prenom, login, password, habitation, quackShelter);

		employe.setDateEmbauche(LocalDate.now());
		employe.setRole(Role.EMPLOYE);
		employe.setAdmin(admin);
		employe.setSalaire(salaire);

		return employe;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public QuackShelter getQuackShelter() {
		return quackShelter;
	}

	public void setQuackShelter(QuackShelter quackShelter) {
		this.quackShelter = quackShelter;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public LocalDate getDateEngagement() {
		return dateEngagement;
	}

	public void setDateEngagement(LocalDate dateEngagement) {
		this.dateEngagement = dateEngagement;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
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

	@Override
	public String toString() {

		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation + ", role=" + role + ", admin=" + admin + ", quackShelter="
				+ quackShelter + ", dateInscription=" + dateInscription + ", dateEngagement=" + dateEngagement
				+ ", salaire=" + salaire + ", dateEmbauche=" + dateEmbauche + "]";
	}

}
