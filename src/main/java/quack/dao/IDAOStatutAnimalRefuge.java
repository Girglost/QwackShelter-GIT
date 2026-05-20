package quack.dao;

import java.util.List;

import quack.model.StatutAnimalRefuge;

public interface IDAOStatutAnimalRefuge {

	public StatutAnimalRefuge findById(Integer id);

	public List<StatutAnimalRefuge> findAll();

	public StatutAnimalRefuge save(StatutAnimalRefuge statut); 

	public StatutAnimalRefuge update(StatutAnimalRefuge statut);

	public void delete(Integer id);
	
	
}
