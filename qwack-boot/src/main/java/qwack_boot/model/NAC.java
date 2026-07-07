package qwack_boot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class NAC extends Mamifere {

	@Column(length = 50, nullable = true)
	private String espece;

	public NAC(String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean sterilisation, boolean gestante, String espece) {
		super(nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter, sterilisation, gestante);
		this.espece = espece;
	}

	public NAC(String nomAnimal, String couleur, Genre genre, QuackShelter qwackShelter, boolean sterilisation,
			boolean gestante, String espece) {
		super(nomAnimal,
				LocalDate.now(),
				couleur,
				null,
				null,
				Famille.Felin,
				genre,
				qwackShelter,
				sterilisation,
				gestante);
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
				+ famille + ", genre=" + genre + ", quackShelter=" + quackShelter + ", statutAnimal=" + statutAnimal
				+ ", espece=" + espece + "]";
	}

}
