package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Chat extends Mamifere {

	private String race;

	public Chat(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante, String race) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, sterilisation, gestante);
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
				+ ", caracteres=" + caracteres + ", qwackShelter=" + qwackShelter + ", historiqueSante="
				+ historiqueSante + ", statutAnimal=" + statutsAnimal + ", race=" + race + "]";
	}
	
	
	
	
}
