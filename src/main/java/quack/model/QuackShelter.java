package quack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QuackShelter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "DECIMAL(20,2)")
	private double tresorerie;
	
	@Column(name = "nb_place", nullable = false)
	private int nbPlace;
	
	@OneToOne
	@JoinColumn(name = "lieu_id")
	private Lieu lieu;
	
	@OneToMany(mappedBy = "quackShelter")
	private List<Personnel> personnel;
	
	@OneToMany(mappedBy = "quackShelter")
    private List<Animal> animaux = new ArrayList<>();
	
	@OneToMany(mappedBy="quackshelter")
	protected List<Visite> visites = new ArrayList(); 
	
	// constructeur vide 
	
	public QuackShelter() {}
	
	// constructeur
	
    public QuackShelter( double tresorerie, int nbPlace, Lieu lieu) {
        this.tresorerie = tresorerie;
        this.nbPlace = nbPlace;
        this.lieu = lieu;
        this.personnel = new ArrayList<>();
    }

    //get set
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTresorerie() {
		return tresorerie;
	}

	public void setTresorerie(double tresorerie) {
		this.tresorerie = tresorerie;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public List<Personnel> getPersonnel() {
		return personnel;
	}

	public void setPersonnel(List<Personnel> personnel) {
		this.personnel = personnel;
	}
	
    
	  //methode pour ajouter un personnel
	
    public void ajouterPersonnel(Personnel personnel) {
        this.personnel.add(personnel);
    }
	
    //methode pour recuperer les animaux du refuge
    
    public List<Animal> getAnimauxDuRefuge() {
        return new ArrayList<>(this.animaux);
    }

  //methode pour verifier le nombre de plavces dans le refuge
    
    public boolean aDesPlacesDisponibles() {
        return this.animaux.size() < this.nbPlace;
    }
    
    
}
