package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Animal;
import quack.model.Genre;

public interface IDAOAnimal extends JpaRepository<Animal,Integer> {

	public List<Animal> findByName(String name);

	public List<Animal> findByGenre(Genre genre);

	public List<Animal> findByType(String type);

	public List<Animal>	findByDispo();
}
