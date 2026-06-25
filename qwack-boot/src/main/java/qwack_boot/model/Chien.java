package qwack_boot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Chien extends Mamifere {

	private String race;

	public Chien(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante, String race) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, sterilisation, gestante);
		this.race = race;
	}

	public Chien(String nomAnimal, String couleur, Genre genre, QuackShelter qwackShelter, boolean sterilisation,
			boolean gestante, String race) {
		super(nomAnimal,
				LocalDate.now(),
				couleur,
				null,
				null,
				Famille.Canin,
				genre,
				qwackShelter,
				sterilisation,
				gestante);
		this.race = race;
	}

	public Chien() {
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Chien [sterilisation=" + sterilisation + ", gestante=" + gestante + ", id=" + id + ", nomAnimal="
				+ nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur=" + couleur + ", regimeAlimentaire="
				+ regimeAlimentaire + ", traitement=" + traitement + ", famille=" + famille + ", genre=" + genre
				+ ", quackShelter=" + quackShelter + ", statutAnimal=" + statutAnimal + ", race=" + race + "]";
	}

}
