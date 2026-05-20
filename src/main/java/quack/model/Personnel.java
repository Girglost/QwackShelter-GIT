package quack.model;

public class Personnel extends Personne{
	protected boolean patron;
	
	
	public Personnel() {}
	
	public Personnel(Integer id, String nom, String prenom, boolean patron, Lieu habitation) {
		super(id, nom, prenom, habitation);
		this.patron = patron;
	}

	public boolean isPatron() {
		return patron;
	}

	public void setPatron(boolean patron) {
		this.patron = patron;
	}

	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", habitation=" + habitation
				+ ", patron=" + patron + "]";
	}
	
	

	
}
