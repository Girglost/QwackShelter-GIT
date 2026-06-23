package quack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Lieu",uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "voie", "ville", "cp"}))

public class  Lieu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private TypeLieu type;
	
	@Embedded
	private Adresse adresse;

	@OneToMany(mappedBy="habitation")
	private List<Personne> personne = new ArrayList<>();
	
	//constructeur vide
	
	 public Lieu() {}
	 
	
	 //constructeur
	 
	 public Lieu(TypeLieu type, String numero, String voie, String ville, String cp) {
		this.type = type;
		this.adresse = new Adresse(numero, voie, ville, cp);
	}

 //get set
	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public TypeLieu getType() {
		 return type;
	 }

	 public void setType(TypeLieu type) {
		 this.type = type;
	 }

	 public Adresse getAdresse() {
		 return adresse;
	 }

	 public void setAdresse(Adresse adresse) {
		 this.adresse = adresse;
	 }
	 
	 public List<Personne> getPersonne() {
		return personne;
	}

	 public void setPersonne(List<Personne> personne) {
		 this.personne = personne;
	 }

	 //to String
	 @Override
	 public String toString() {
		return "Lieu [id=" + id + ", type=" + type + ", adresse=" + adresse+"]";
	 }

}
