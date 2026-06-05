package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Patron;
import quack.model.Personne;
import quack.model.Visiteur;

public interface IDAOPersonne extends JpaRepository<Personne,Integer>{
	@Query("FROM Patron")
	public List<Patron> findAllPatron();
	@Query("FROM Visiteur")
	public List<Visiteur> findAllVisiteur();
	@Query("FROM Employe")
	public List<Employe> findAllEmploye();
	@Query("FROM Benevole")
	public List<Benevole> findAllBenevole();
	
	public Personne findByLoginAndPassword(String login,String password);
//	
//	public Personne findbyIdwithVisite();
//	public Personne findbyIdwithAdoption();
	
}
