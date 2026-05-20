package quack.model;

import java.util.ArrayList;
import java.util.List;

public class QuackShelter {
	
	private Integer id;
	private double tresorerie;
	private int nbPlace;
	
	private Lieu lieu;
	
	private List<Personnel> personnel;
	
	
	// constructeur vide 
	
	public QuackShelter() {}
	
	// constructeur
	
    public QuackShelter(Integer id, double tresorerie, int nbPlace, Lieu lieu) {
        this.id = id;
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
        // On passerait par StatutAnimalRefuge ou Lieu selon le besoin
    }

}
