package quack.model;

public abstract class  Lieu {
	
	protected Integer id;
	protected String type;
	private Adresse adresse;

	
	//constructeur 
	
	 public Lieu(int id, String type, Adresse adresse) {
	        this.id = id;
	        this.type = type;
	        this.adresse = adresse;
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
