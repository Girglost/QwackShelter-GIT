package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOHistoriqueSante;
import quack.model.HistoriqueSante;

@Service
public class HistoriqueSanteService {
	
	@Autowired
	IDAOHistoriqueSante daoHistoriqueSante;
	
	// --------------- CRUD ----------------
	
	public List<HistoriqueSante> getAll(){
		return daoHistoriqueSante.findAll();
	}
	public HistoriqueSante getById(Integer id) {
		return  daoHistoriqueSante.findById(id).orElse(null);
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
