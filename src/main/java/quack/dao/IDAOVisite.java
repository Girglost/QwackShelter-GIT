package quack.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quack.model.Emplacement;
import quack.model.Visite;

public interface IDAOVisite extends JpaRepository<Visite,Integer> {
	
	@Query("SELECT v from Visite v where v.visiteur.id=:idPersonne")
	public List<Visite> findByIdPersonne(@Param("idPersonne")Integer idPersonne);
	
	@Query("SELECT v from Visite v where v.quackshelter.id=:idShelter")
	public List<Visite> findByIdShelter(@Param("idShelter")Integer idShelter);

	@Query("SELECT v  from Visite v where v.dateVisite =:date")
	public List<Visite> findAllByDate(@Param("date")LocalDateTime date);

}
