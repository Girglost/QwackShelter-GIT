package qwack_boot.model;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "emplacement")
@Check(constraints = "nb_place >= 1")
public class Emplacement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Min(value=1)
	@Column(name = "nb_place", nullable = false)
	private int nbPlace;

	@Column(nullable = false)
	private boolean complet;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type_box")
	private typeBox box;

	public Emplacement(int nbPlace, boolean complet, typeBox box) {

		this.nbPlace = nbPlace;
		this.complet = complet;
		this.box = box;
	}

	public Emplacement() {
	}

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
