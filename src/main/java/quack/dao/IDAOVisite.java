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
	
	@Query("SELECT v from Visite v where v.quackShelter.id=:idShelter")
	public List<Visite> findByIdShelter(@Param("idShelter")Integer idShelter);

	@Query("SELECT v  from Visite v where v.dateVisite =:date")
	public List<Visite> findAllByDateExacte(@Param("date")LocalDateTime date);
	
	
	public List<Visite> findAllByDateVisiteBetween(LocalDateTime dateDebut,LocalDateTime dateFin);

	@Query("SELECT count(v) from Visite v where v.animal.id=:idAnimal AND v.visiteur.id =:idVisiteur")
	public long NbVisitesByIdAnimalAndIdVisiteur(@Param("idAnimal")int idAnimal, @Param("idVisiteur")int idVisiteur);
	@Query("SELECT count(v) from Visite v where v.animal.id=:idAnimal")
	public long NbVisitesByIdAnimal(@Param("idAnimal")int idAnimal);
	@Query("SELECT count(v) from Visite v where v.visiteur.id=:idVisiteur")
	public long NbVisitesByIdVisiteur(@Param("idVisiteur")int idVisiteur);

}