package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOEmplacement;
import qwack_boot.model.Emplacement;
import qwack_boot.model.typeBox;

@Service
public class EmplacementService {

	@Autowired
	IDAOEmplacement daoEmplacement;

	// --------------- CRUD ----------------

	public List<Emplacement> getAll() {
		return daoEmplacement.findAll();
	}

	public Emplacement getById(Integer id) {
		return daoEmplacement.findById(id).orElse(null);
	}

	public void insert(Emplacement emplacement) {
		daoEmplacement.save(emplacement);
	}

	public void update(Emplacement emp) {
		daoEmplacement.save(emp);
	}

	public void delete(Integer id) {
		daoEmplacement.deleteById(id);
	}

	// --------------- Perso ----------------

	public List<Emplacement> getByCompletTrue(){
		return daoEmplacement.findByCompletTrue();
	}
	
	public List<Emplacement> getByCompletFalse(){
		return daoEmplacement.findByCompletFalse();

	}
	
	public List<Emplacement> getByBox(typeBox box){
		return daoEmplacement.findByBox(box);
	}
}
