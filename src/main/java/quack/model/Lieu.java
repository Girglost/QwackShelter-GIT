package quack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lieu")
public class  Lieu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40, nullable=false)
	protected String type;
	
	@Embedded
	private Adresse adresse;

	@OneToMany(mappedBy="habitation")
	private List<Personne> personne = new ArrayList<>();
	
	//constructeur vide
	
	 public Lieu() {}
	 
	
	 //constructeur
	 
	 public Lieu(String type, String numero, String voie, String ville, String cp) {
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

	 public String getType() {
		 return type;
	 }

	 public void setType(String type) {
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
		return "Lieu [id=" + id + ", type=" + type + ", adresse=" + adresse + ", personne=" + personne + "]";
	 }

}
