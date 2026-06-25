package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quack.model.Animal;
import quack.model.Statut;
import quack.model.StatutAnimal;

public interface IDAOStatutAnimal extends JpaRepository<StatutAnimal,Integer>{
	@Query("SELECT s FROM StatutAnimal s WHERE s.dateDepart is null")
	public List<Animal>	findByDispo();
	@Query("SELECT s FROM StatutAnimal s WHERE s.adoptant.id =:idPersonne")
	public List<StatutAnimal> findByAdoptant(@Param("idPersonne")Integer idPersonne);
	
	public List<StatutAnimal> findByStatut(Statut statut);
}
