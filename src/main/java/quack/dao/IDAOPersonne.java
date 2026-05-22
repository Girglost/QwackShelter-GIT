package quack.dao;

import java.util.List;

import quack.model.Admin;
import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Personne;
import quack.model.Visiteur;

public interface IDAOPersonne extends IDAO<Personne,Integer>{

	public List<Admin> findAllAdmin();
	
	public List<Visiteur> findAllVisiteur();
	
	public List<Employe> findAllEmploye();
	
	public List<Benevole> findAllBenevole();
//	
//	public Personne findbyIdwithVisite();
//	public Personne findbyIdwithAdoption();
	
}
