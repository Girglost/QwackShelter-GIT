package quack.model;

public class Emplacement {

	private Integer id;
	private int nbPlace;
	private boolean complet;
	private typeBox box;
	
	public Emplacement(Integer id, int nbPlace, boolean complet, typeBox box) {
		super();
		this.id = id;
		this.nbPlace = nbPlace;
		this.complet = complet;
		this.box = box;
	}
	
	public Emplacement(int nbPlace, boolean complet, typeBox box) {
		this.nbPlace = nbPlace;
		this.complet = complet;
		this.box = box;
	}
	
	public Emplacement() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public boolean isComplet() {
		return complet;
	}

	public void setComplet(boolean complet) {
		this.complet = complet;
	}

	public typeBox getBox() {
		return box;
	}

	public void setBox(typeBox box) {
		this.box = box;
	}

	@Override
	public String toString() {
		return "Emplacement [id=" + id + ", nbPlace=" + nbPlace + ", complet=" + complet + ", box=" + box + "]";
	}
	
	
	
}
