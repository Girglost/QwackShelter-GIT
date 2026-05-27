package quack.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OISEAU")
public abstract class Oiseaux extends Animal {
    
	@Column(name="capacite_vol")
    protected boolean capaciteVol;  
    protected boolean pondeuse;
    
    
	public Oiseaux(int id, String nomAnimal, LocalDate dateNaissance, String couleur, String regimeAlimentaire,
			String traitement, Famille famille, Genre genre, List<Caractere> caracteres, QuackShelter qwackShelter,
			boolean capaciteVol, boolean pondeuse) {
		super(id, nomAnimal, dateNaissance, couleur, regimeAlimentaire, traitement, famille, genre, caracteres,
				qwackShelter);
		this.capaciteVol = capaciteVol;
		this.pondeuse = pondeuse;
	}


	public Oiseaux() {

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
		return "Oiseaux [id=" + id + ", nomAnimal=" + nomAnimal + ", dateNaissance=" + dateNaissance + ", couleur="
				+ couleur + ", regimeAlimentaire=" + regimeAlimentaire + ", traitement=" + traitement + ", famille="
				+ famille + ", genre=" + genre + ", caracteres=" + caracteres + ", qwackShelter=" + quackShelter
				+ ", historiqueSante=" + historiqueSante + ", statutAnimal=" + statutsAnimal + ", capaciteVol="
				+ capaciteVol + ", pondeuse=" + pondeuse + "]";
	}
	
	
	
	
    
    

}
