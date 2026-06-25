package qwack_boot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public abstract class Mamifere extends Animal {

	protected boolean sterilisation;
	protected boolean gestante;

	public Mamifere(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter);
		this.sterilisation = sterilisation;
		this.gestante = gestante;
	}

	public Mamifere() {
	}

	public Mamifere(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre,
				qwackShelter);
		this.sterilisation = sterilisation;
		this.gestante = gestante;
	}

	public boolean isSterilisation() {
		return sterilisation;
	}

	public boolean isGestante() {
		return gestante;
	}

	public void setSterilisation(boolean sterilisation) {
		this.sterilisation = sterilisation;
	}

	public void setGestante(boolean gestante) {
		this.gestante = gestante;
	}

	@Override
	public String toString() {
		return "Mamifere [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", quackShelter=" + quackShelter + ", statutAnimal=" + statutAnimal
				+ ", sterilisation=" + sterilisation + ", gestante=" + gestante + "]";
	}

}
