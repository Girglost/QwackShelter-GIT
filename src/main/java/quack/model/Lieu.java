package quack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lieu")
public abstract class  Lieu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 40, nullable=false)
	protected String type;
	
	@Embedded
	private Adresse adresse;

	
	//constructeur 
	
	 public Lieu(int id, String type, String numero, String voie, String ville, String cp) {
	        this.id = id;
	        this.type = type;
	        this.adresse = new Adresse(numero,voie,ville,cp);
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

	 //to String 
	 
	 @Override
	 public String toString() {
		return "Lieu [id=" + id + ", type=" + type + ", adresse=" + adresse + "]";
	 }
	 
	 
}
