package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOEmplacement;
import quack.dao.IDAOVisite;
import quack.model.Emplacement;
import quack.model.Visite;

@Service
public class VisiteService {

	@Autowired
	IDAOEmplacement daoEmplacement;
	
	@Autowired
	IDAOVisite daoVisite;
	
	// --------------- CRUD ----------------
	
	public List<Emplacement> getAll(){
		return daoEmplacement.findAll();
	}
	public Emplacement getById(Integer id) {
		return  daoEmplacement.findById(id).orElse(null);
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
	
	public List<Visite> getByIdPersonne(Integer idPersonne){
		return daoVisite.findByIdPersonne(idPersonne);
	}
	
	public List<Visite> getByIdShelter(Integer idShelter){
		return daoVisite.findByIdShelter(idShelter);
	}
	
	
}
