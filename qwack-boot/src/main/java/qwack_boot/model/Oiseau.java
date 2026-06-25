package qwack_boot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public abstract class Oiseau extends Animal {

	@Column(name = "capacite_vol")
	protected boolean capaciteVol;
	protected boolean pondeuse;

	public Oiseau(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean capaciteVol, boolean pondeuse) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter);
		this.capaciteVol = capaciteVol;
		this.pondeuse = pondeuse;
	}

	public Oiseau(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, QuackShelter qwackShelter,
			boolean capaciteVol, boolean pondeuse) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre,
				qwackShelter);
		this.capaciteVol = capaciteVol;
		this.pondeuse = pondeuse;
	}

	public Oiseau() {

	}

	public boolean isCapaciteVol() {
		return capaciteVol;
	}

	public void setCapaciteVol(boolean capaciteVol) {
		this.capaciteVol = capaciteVol;
	}

	public boolean isPondeuse() {
		return pondeuse;
	}

	public void setPondeuse(boolean pondeuse) {
		this.pondeuse = pondeuse;
	}

	@Override
	public String toString() {
		return "Oiseau [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", quackShelter=" + quackShelter + ", statutAnimal=" + statutAnimal
				+ ", capaciteVol=" + capaciteVol + ", pondeuse=" + pondeuse + "]";
	}

}
