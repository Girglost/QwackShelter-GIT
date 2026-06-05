package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Patron;
import quack.model.Personne;
import quack.model.Visiteur;

public interface IDAOPersonne extends JpaRepository<Personne,Integer>{

	public List<Patron> findAllPatron();
	
	public List<Visiteur> findAllVisiteur();
	
	public List<Employe> findAllEmploye();
	
	public List<Benevole> findAllBenevole();
	
	public Personne findByLoginAndPassword(String login,String password);
//	
//	public Personne findbyIdwithVisite();
//	public Personne findbyIdwithAdoption();
	
}
