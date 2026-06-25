package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOHistoriqueSante;
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

}
