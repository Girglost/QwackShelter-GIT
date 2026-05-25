package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class NAC extends Animal{

	private String espece;

	public NAC(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			String espece) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter);
		this.espece = espece;
	}

	public NAC() {
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	@Override
	public String toString() {
		return "NAC [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", caracteres=" + caracteres + ", qwackShelter=" + qwackShelter
				+ ", historiqueSante=" + historiqueSante + ", statutAnimal=" + statutAnimal + ", espece=" + espece
				+ "]";
	}
	
	
	
}
