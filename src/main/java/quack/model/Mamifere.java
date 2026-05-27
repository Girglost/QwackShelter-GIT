package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public abstract class Mamifere extends Animal{

	protected boolean sterilisation;
	protected boolean gestante;
	


	public Mamifere(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter);
		this.sterilisation = sterilisation;
		this.gestante = gestante;
	}


	public Mamifere() {
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
				+ famille + ", genre=" + genre + ", caractere=" + caracteres + ", qwackShelter=" + quackShelter
				+ ", historiqueSante=" + historiqueSante + ", sterilisation=" + sterilisation + ", gestante=" + gestante
				+ "]";
	}
	
	
}
