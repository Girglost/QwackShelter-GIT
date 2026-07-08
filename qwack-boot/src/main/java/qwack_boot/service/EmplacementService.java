package qwack_boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOEmplacement;
import qwack_boot.model.Emplacement;
import qwack_boot.model.typeBox;

@Service
public class EmplacementService {

	final IDAOEmplacement daoEmplacement;

	EmplacementService(IDAOEmplacement daoEmplacement) {
		this.daoEmplacement = daoEmplacement;
	}

	// --------------- CRUD ----------------

	public List<Emplacement> getAll() {
		return daoEmplacement.findAll();
	}

	public Emplacement getById(Integer id) {
		return daoEmplacement.findById(id).orElse(null);
	}

	public Emplacement insert(Emplacement emplacement) {
		// Emplacement emp = new Emplacement();

		// emp.setBox(emplacement.box());
		// emp.setComplet(emplacement.complet());
		// emp.setNbPlace(emplacement.nb_place());

		return daoEmplacement.save(emplacement);
	}

	public Emplacement update(Integer id, Emplacement emplacement) {
		// Emplacement emp = daoEmplacement.findById(id).orElse(null);

		// emp.setBox(emplacement.box());
		// emp.setComplet(emplacement.complet());
		// emp.setNbPlace(emplacement.nb_place());
		emplacement.setId(id);

		return daoEmplacement.save(emplacement);
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
