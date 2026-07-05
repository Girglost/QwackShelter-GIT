package qwack_boot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOHistoriqueSante;
import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

@Service
public class HistoriqueSanteService {

	@Autowired
	IDAOHistoriqueSante daoHistoriqueSante;

	// --------------- CRUD ----------------

	public List<HistoriqueSante> getAll() {
		return daoHistoriqueSante.findAll();
	}

	public HistoriqueSante getById(Integer id) {
		return daoHistoriqueSante.findById(id).orElse(null);
	}

	public void insert(HistoriqueSante sante) {
		daoHistoriqueSante.save(sante);
	}

	public void update(HistoriqueSante sante) {
		daoHistoriqueSante.save(sante);
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
