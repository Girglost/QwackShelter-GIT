package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import quack.dao.IDAOQuackShelter;
import quack.model.Lieu;
import quack.model.QuackShelter;

public class QuackShelterService {

	@Autowired
	IDAOQuackShelter daoQuackShelter;
	
	// --------------- CRUD ----------------
	
	public List<QuackShelter> getAll(){
		return daoQuackShelter.findAll();
	}
	public QuackShelter getById(Integer id) {
		return  daoQuackShelter.findById(id).orElse(null);
	}
	public void insert(QuackShelter quack) {
		daoQuackShelter.save(quack);
	}
	public void update(QuackShelter quack) {
		daoQuackShelter.save(quack);
	}
	public void delete(Integer id) {
		daoQuackShelter.deleteById(id);
	}
}
