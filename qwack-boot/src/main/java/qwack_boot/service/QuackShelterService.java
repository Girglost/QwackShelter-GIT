package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.QuackShelter;

@Service
public class QuackShelterService {

	@Autowired
	IDAOQuackShelter daoQuackShelter;

	// --------------- CRUD ----------------

	public List<QuackShelter> getAll() {
		return daoQuackShelter.findAll();
	}

	public QuackShelter getById(Integer id) {
		return daoQuackShelter.findById(id).orElse(null);
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
