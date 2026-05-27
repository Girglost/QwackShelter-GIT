package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Canard extends Oiseaux {
    
	@Column(length = 50, nullable = true)
    protected String race;
    @Column(name="sauvage")
    protected boolean estSauvage;
    
    
	public Canard(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean capaciteVol, boolean pondeuse, String race, boolean estSauvage) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, capaciteVol, pondeuse);
		this.race = race;
		this.estSauvage = estSauvage;
	}
	public Canard() {
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public boolean isestSauvage() {
		return estSauvage;
	}
	public void setestSauvage(boolean estSauvage) {
		this.estSauvage = estSauvage;
	}
	@Override
	public String toString() {
		return "Poule [capaciteVol=" + capaciteVol + ", pondeuse=" + pondeuse + ", id=" + id + ", nomAnimal="
				+ nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur=" + couleur + ", regimeAlimentaire="
				+ regimeAlimentaire + ", traitement=" + traitement + ", famille=" + famille + ", genre=" + genre
				+ ", caracteres=" + caracteres + ", qwackShelter=" + qwackShelter + ", historiqueSante="
				+ historiqueSante + ", statutAnimal=" + statutAnimal + ", race=" + race + ", estSauvage=" + estSauvage
				+ "]";
	}
    
    
    
}
