package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Animal;
import quack.model.Genre;

public interface IDAOAnimal extends JpaRepository<Animal,Integer> {

	
	public List<Animal> findByNomAnimal(String name);

	public List<Animal> findByGenre(Genre genre);

	public List<Animal> findByFamille(String type);
/*
	@Query("SELECT a FROM Animal a WHERE a.statutAnimal.dateDepart is null")
	public List<Animal>	findByDispo();*/
}
