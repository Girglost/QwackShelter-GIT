package qwack_boot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOHistoriqueSante;
import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

@Service
public class HistoriqueSanteService {

	final IDAOHistoriqueSante daoHistoriqueSante;
	final AnimalService animalSrv;


	HistoriqueSanteService(IDAOHistoriqueSante daoHistoriqueSante, AnimalService animalSrv) {
		this.daoHistoriqueSante = daoHistoriqueSante;
		this.animalSrv = animalSrv;
	}

	// --------------- CRUD ----------------

	public List<HistoriqueSante> getAll() {
		return daoHistoriqueSante.findAll();
	}

	public HistoriqueSante getById(Integer id) {
		return daoHistoriqueSante.findById(id).orElse(null);
	}

	public HistoriqueSante insert(HistoriqueSante sante) {
		// HistoriqueSante hs = new HistoriqueSante();

		// hs.setCommentaire(sante.commentaire());
		// hs.setCause(sante.cause());
		// hs.setPoids(sante.poids());
		// hs.setAnimal(animalSrv.getById(sante.animalId()));

		return daoHistoriqueSante.save(sante);
	}

	public HistoriqueSante update(Integer id, HistoriqueSante sante) {
		// HistoriqueSante hs = daoHistoriqueSante.findById(id).orElse(null);

		// hs.setCommentaire(sante.commentaire());
		// hs.setPoids(sante.poids());
		// hs.setDate(sante.date());
		// hs.setHeure(sante.heure());
		// hs.setDuree(sante.duree());
		// hs.setCause(sante.cause());
		// hs.setAnimal(animalSrv.getById(sante.animalId()));

		sante.setId(id);
		return daoHistoriqueSante.save(sante);
	}

	public void delete(Integer id) {
		daoHistoriqueSante.deleteById(id);
	}



	public List<HistoriqueSante> getByCause(Cause cause) {
		return daoHistoriqueSante.findByCause(cause);
	}
	public List<HistoriqueSante> getByCommentaireNotNull() {
		return daoHistoriqueSante.findByCommentaireNotNull();
	}
	public List<HistoriqueSante> getByDateBetween(LocalDate a, LocalDate b) {
		return daoHistoriqueSante.findByDateBetween(a,b);
	}
	public List<HistoriqueSante> getByDateAfter(LocalDate a) {
		return daoHistoriqueSante.findByDateAfter(a);
	}
	public List<HistoriqueSante> getByDateBefore(LocalDate a) {
		return daoHistoriqueSante.findByDateBefore(a);
	}
	public List<HistoriqueSante> getByAnimalId(Integer AnimlaId) {
		return daoHistoriqueSante.findByAnimalId(AnimlaId);
	}


}
