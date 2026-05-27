package quack.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// On ne la persiste pas en BDD
public abstract class Personnel extends Personne{

	@ManyToOne
    @JoinColumn(name = "quack_shelter_id", nullable = false)
    private QuackShelter quackShelter;
    
    
	public Personnel() {}
	public Personnel(Integer id, String nom, String prenom, String login, String password, Lieu habitation,
			boolean patron) {
		super(id, nom, prenom, login, password, habitation);
	}

	public QuackShelter getQuackShelter() { return quackShelter; }
    public void setQuackShelter(QuackShelter quackShelter) {
        this.quackShelter = quackShelter;
    }
	
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password="
				+ password + ", habitation=" + habitation+"]";
	}
	
	
}
