package quack.dao;

import java.util.List;

import quack.model.StatutAnimal;

public interface IDAOStatutAnimalRefuge {

	public StatutAnimal findById(Integer id);

	public List<StatutAnimal> findAll();

	public StatutAnimal save(StatutAnimal statut); 

	public StatutAnimal update(StatutAnimal statut);

	public void deleteById(Integer id);
	
	public void delete(StatutAnimal statut);
	
	
	
	
}
