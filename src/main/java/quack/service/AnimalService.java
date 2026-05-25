package quack.service;

import java.util.List;

import quack.dao.DAOAnimal;
import quack.model.Animal;

public class AnimalService {

	static DAOAnimal daoAnimal = new DAOAnimal();
	
	public List<Animal> getAll()
	{
		return daoAnimal.findAll();
	}
	
	public Animal getById(Integer id) 
	{
		return daoAnimal.findById(id);
	}
	
	public void insert(Animal matiere) 
	{
		daoAnimal.save(matiere);
	}
	
	public void update(Animal matiere) 
	{
		daoAnimal.update(matiere);
	}

	public void deleteById(Integer id) 
	{
		daoAnimal.deleteById(id);
	}
}
