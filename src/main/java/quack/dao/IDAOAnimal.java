package quack.dao;

import java.util.List;

import quack.model.Animal;

public interface IDAOAnimal {

	public Animal findById(Integer id);
	
	public List<Animal> findAll();
	
	public Animal save(Animal animal); 

	public Animal update(Animal animal);

	public void delete(Integer id);

	
}
