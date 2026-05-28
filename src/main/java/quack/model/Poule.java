package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Poule extends Oiseau {
    
	@Column(length = 50, nullable = true)
    protected String race;
    
	public Poule(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean capaciteVol, boolean pondeuse, String race, boolean couveuse) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, capaciteVol, pondeuse);
		this.race = race;
	}
	public Poule() {
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Poule [capaciteVol=" + capaciteVol + ", pondeuse=" + pondeuse + ", id=" + id + ", nomAnimal="
				+ nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur=" + couleur + ", regimeAlimentaire="
				+ regimeAlimentaire + ", traitement=" + traitement + ", famille=" + famille + ", genre=" + genre
				+ ", caracteres=" + caracteres + ", qwackShelter=" + quackShelter + ", historiqueSante="
				+ historiqueSante + ", statutAnimal=" + statutsAnimal + ", race=" + race + "]";
	}
    
    
    
}
