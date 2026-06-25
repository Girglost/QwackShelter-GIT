package qwack_boot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOVisite;
import qwack_boot.model.Visite;

@Service
public class VisiteService {

	@Autowired
	IDAOVisite daoVisite;

	// --------------- CRUD ----------------

	public List<Visite> getAll() {
		return daoVisite.findAll();
	}

	public Visite getById(Integer id) {
		return daoVisite.findById(id).orElse(null);
	}

	public void insert(Visite visite) {
		daoVisite.save(visite);
	}

	public void update(Visite emp) {
		daoVisite.save(emp);
	}

	public void delete(Integer id) {
		daoVisite.deleteById(id);
	}

	public List<Visite> getByIdPersonne(Integer idPersonne) {
		return daoVisite.findByIdPersonne(idPersonne);
	}

	public List<Visite> getByIdShelter(Integer idShelter) {
		return daoVisite.findByIdShelter(idShelter);
	}

	/*
	 * public List<Visite> getAllByDateExacte(LocalDateTime date) {
	 * 
	 * return daoVisite.findAllByDateExacte(date);
	 * }
	 */
	public List<Visite> getAllByDateVisiteBetween(LocalDateTime dateDebut, LocalDateTime dateFin) {

		return daoVisite.findAllByDateVisiteBetween(dateDebut, dateFin);
	}

	public long NbVisitesByIdAnimalAndIdVisiteur(int idAnimal, int idVisiteur) {
		return daoVisite.NbVisitesByIdAnimalAndIdVisiteur(idAnimal, idVisiteur);
	}

	public long NbVisitesByIdAnimal(int idAnimal) {
		return daoVisite.NbVisitesByIdAnimal(idAnimal);
	}

	public long NbVisitesByIdVisiteur(int idVisiteur) {
		return daoVisite.NbVisitesByIdVisiteur(idVisiteur);
	}

}
