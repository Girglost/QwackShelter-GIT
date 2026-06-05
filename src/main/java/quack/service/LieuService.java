package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import quack.dao.IDAOLieu;
import quack.model.Lieu;

public class LieuService {

	
	@Autowired
	IDAOLieu daoLieu;
	
	// --------------- CRUD ----------------
	
	public List<Lieu> getAll(){
		return daoLieu.findAll();
	}
	public Lieu getById(Integer id) {
		return  daoLieu.findById(id).orElse(null);
	}
	public void insert(Lieu lieu) {
		daoLieu.save(lieu);
	}
	public void update(Lieu lieu) {
		daoLieu.save(lieu);
	}
	public void delete(Integer id) {
		daoLieu.deleteById(id);
	}
	
}
