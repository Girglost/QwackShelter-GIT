package quack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="emplecement")
public class Emplacement {

	@Id
	private Integer id;
	
	//Je veux mettre une condition min=1
	@Column(name="nb_place",nullable=false)
	private int nbPlace;
	
	@Column(nullable=false)
	private boolean complet;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, name="type_box")
	private typeBox box;
	
	public Emplacement(Integer id, int nbPlace, boolean complet, typeBox box) {
		this.id = id;
		this.nbPlace = nbPlace;
		this.complet = complet;
		this.box = box;
	}
	
	public Emplacement() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public boolean isComplet() {
		return complet;
	}

	public void setComplet(boolean complet) {
		this.complet = complet;
	}

	public typeBox getBox() {
		return box;
	}

	public void setBox(typeBox box) {
		this.box = box;
	}

	@Override
	public String toString() {
		return "Emplacement [id=" + id + ", nbPlace=" + nbPlace + ", complet=" + complet + ", box=" + box + "]";
	}
	
	
	
}
