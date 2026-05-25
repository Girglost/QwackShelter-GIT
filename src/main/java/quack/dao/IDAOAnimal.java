package quack.dao;

import java.util.List;

import quack.model.Animal;

public interface IDAOAnimal extends IDAO<Animal,Integer> {

	public Animal update(Animal animal);

public Animal findByName(Animal String);

	
}
