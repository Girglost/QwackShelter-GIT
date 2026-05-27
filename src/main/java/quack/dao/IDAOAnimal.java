package quack.dao;

import java.util.List;

import quack.model.Animal;
import quack.model.Genre;

public interface IDAOAnimal extends IDAO<Animal,Integer> {

	public Animal update(Animal animal);

	public List<Animal> findByName(String name);

	public List<Animal> findByGenre(Genre genre);

	public List<Animal> findByType(String type);

	public List<Animal>	findByDispo();
}
