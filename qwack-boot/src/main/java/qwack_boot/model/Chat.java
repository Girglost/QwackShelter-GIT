package qwack_boot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Chat extends Mamifere {

	private String race;

	public Chat(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante, String race, String description) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, sterilisation, gestante, description);
		this.race = race;
	}

	public Chat(String nomAnimal, String couleur, Genre genre, QuackShelter qwackShelter, boolean sterilisation,
			boolean gestante, String race, String description) {
		super(nomAnimal,
				LocalDate.now(),
				couleur,
				null,
				null,
				Famille.Felin,
				genre,
				qwackShelter,
				sterilisation,
				gestante,
				description);
		this.race = race;
	}

	public Chat() {
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Chat [sterilisation=" + sterilisation + ", gestante=" + gestante + ", id=" + id + ", nomAnimal="
				+ nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur=" + couleur + ", regimeAlimentaire="
				+ regimeAlimentaire + ", traitement=" + traitement + ", famille=" + famille + ", genre=" + genre
				+ ", quackShelter=" + quackShelter + ", statutAnimal=" + statutAnimal + ", race=" + race + "]";
	}

}
