package qwack_boot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOStatutAnimal;
import qwack_boot.model.Animal;
import qwack_boot.model.Personne;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;

@Service
public class StatutAnimalService {

	@Autowired
	IDAOStatutAnimal daoStatutAnimal;

	// --------------- CRUD ----------------

	public List<StatutAnimal> getAll() {
		return daoStatutAnimal.findAll();
	}

	public StatutAnimal getById(Integer id) {
		return daoStatutAnimal.findById(id).orElse(null);
	}

	public void insert(StatutAnimal statut) {
		daoStatutAnimal.save(statut);
	}

	public void update(StatutAnimal statut) {
		daoStatutAnimal.save(statut);
	}

	public void delete(Integer id) {
		daoStatutAnimal.deleteById(id);
	}

	// --------------- Special ----------------

	public List<Animal> getByDispo(){
		return  daoStatutAnimal.findByDispo();
	}

	public List<StatutAnimal> getByAdoptantId(Integer idPersonne) {
		return daoStatutAnimal.findByAdoptantId(idPersonne);
	}

	public StatutAnimal getByAnimalId(@Param("idAnimal") Integer idAnimal){
		return daoStatutAnimal.findByAnimalId(idAnimal);
	}

	public List<StatutAnimal> getByStatut(Statut statut) {
		return daoStatutAnimal.findByStatut(statut);
	}

	public List<StatutAnimal> getByDateArriveeBetween(LocalDate a, LocalDate b){
		return daoStatutAnimal.findByDateArriveeBetween(a,b);
	}
	public List<StatutAnimal> getByDateArriveeAfter(LocalDate a){
		return daoStatutAnimal.findByDateArriveeAfter(a);
	}
	public List<StatutAnimal> getByDateArriveeBefore(LocalDate a){
		return daoStatutAnimal.findByDateArriveeBefore(a);
	}

	public List<StatutAnimal> getByDateDepartBetween(LocalDate a, LocalDate b){
		return daoStatutAnimal.findByDateDepartBetween(a,b);
	}
	public List<StatutAnimal> getByDateDepartAfter(LocalDate a){
		return daoStatutAnimal.findByDateDepartAfter(a);
	}
	public List<StatutAnimal> getByDateDepartBefore(LocalDate a){
		return daoStatutAnimal.findByDateDepartBefore(a);
	}
}
