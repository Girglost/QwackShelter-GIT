package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quack.model.Animal;
import quack.model.StatutAnimal;

public interface IDAOStatutAnimal extends JpaRepository<StatutAnimal,Integer>{
	@Query("SELECT s FROM StatutAnimal s WHERE s.dateDepart is null")
	public List<Animal>	findByDispo();
}
