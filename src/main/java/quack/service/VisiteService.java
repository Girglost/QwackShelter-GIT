package quack.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOVisite;
import quack.model.Visite;

@Service
public class VisiteService {

	@Autowired
	IDAOVisite daoVisite;
	
	
	// --------------- CRUD ----------------
	
	public List<Visite> getAll(){
		return daoVisite.findAll();
	}
	public Visite getById(Integer id) {
		return  daoVisite.findById(id).orElse(null);
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
	
	public List<Visite> getByIdPersonne(Integer idPersonne){
		return daoVisite.findByIdPersonne(idPersonne);
	}
	
	public List<Visite> getByIdShelter(Integer idShelter){
		return daoVisite.findByIdShelter(idShelter);
	}
	public List<Visite> getAllByDate(LocalDateTime date) {
		
		return daoVisite.findAllByDate(date);
	}
	
}
