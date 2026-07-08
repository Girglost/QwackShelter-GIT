package qwack_boot.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qwack_boot.api.requestDTO.statutAnimal.CreateStatutAnimalRequest;
import qwack_boot.api.requestDTO.statutAnimal.UpdateStatutAnimalRequest;
import qwack_boot.api.responseDTO.animal.AnimalResponse;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;

public interface IDAOStatutAnimal extends JpaRepository<StatutAnimal, Integer> {
	@Query("SELECT s.animal FROM StatutAnimal s WHERE s.dateDepart is null")
	public List<AnimalResponse> findByDispo();

	@Query("SELECT s FROM StatutAnimal s WHERE s.adoptant.id =:idPersonne")
	public List<StatutAnimal> findByAdoptantId(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT s FROM StatutAnimal s WHERE s.animal.id =:idAnimal")
	public StatutAnimal findByAnimalId(@Param("idAnimal") Integer idAnimal);

	public List<StatutAnimal> findByStatut(Statut statut);

	public List<StatutAnimal> findByDateArriveeBetween(LocalDate a, LocalDate b);
	public List<StatutAnimal> findByDateArriveeAfter(LocalDate a);
	public List<StatutAnimal> findByDateArriveeBefore(LocalDate a);

	public List<StatutAnimal> findByDateDepartBetween(LocalDate a, LocalDate b);
	public List<StatutAnimal> findByDateDepartAfter(LocalDate a);
	public List<StatutAnimal> findByDateDepartBefore(LocalDate a);

    public StatutAnimal save(CreateStatutAnimalRequest hsr);
	public StatutAnimal save(UpdateStatutAnimalRequest statut);


}
