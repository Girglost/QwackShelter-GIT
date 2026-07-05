package qwack_boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

public interface IDAOHistoriqueSante extends JpaRepository<HistoriqueSante, Integer> {

	public List<HistoriqueSante> findByCause(Cause cause);

	public List<HistoriqueSante> findByCommentaireNotNull();

	public List<HistoriqueSante> findByDateBetween(LocalDate a, LocalDate b);
	public List<HistoriqueSante> findByDateAfter(LocalDate a);
	public List<HistoriqueSante> findByDateBefore(LocalDate a);

	@Query("SELECT h FROM HistoriqueSante h WHERE h.animal.id =:idAnimal")
	public List<HistoriqueSante> findByAnimalId(@Param("idAnimal") Integer idAnimal);

}
