package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quack.model.Emplacement;
import quack.model.Visite;

public interface IDAOVisite extends JpaRepository<Visite,Integer> {
	
	@Query("SELECT v from Visite v where v.visiteur=:idPersonne")
	public List<Visite> findByIdPersonne(@Param("idPersonne")Integer idPersonne);
	
	@Query("SELECT v from Visite v where v.quackshelter=:idShelter")
	public List<Visite> findByIdShelter(@Param("idShelter")Integer idShelter);

}
