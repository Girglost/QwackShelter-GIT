package qwack_boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qwack_boot.model.Animal;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;

public interface IDAOStatutAnimal extends JpaRepository<StatutAnimal, Integer> {
	@Query("SELECT s FROM StatutAnimal s WHERE s.dateDepart is null")
	public List<Animal> findByDispo();

	@Query("SELECT s FROM StatutAnimal s WHERE s.adoptant.id =:idPersonne")
	public List<StatutAnimal> findByAdoptant(@Param("idPersonne") Integer idPersonne);

	public List<StatutAnimal> findByStatut(Statut statut);
}
