package quack.dao;

import java.util.List;

import quack.model.Personne;

public interface IDAOPersonne extends IDAO<Personne,Integer>{

	public Personne findById(Integer id);

	public List<Personne> findAll();

	public Personne save(Personne personne); 

	public Personne update(Personne personne);

	public void delete(Integer id);
	
}
