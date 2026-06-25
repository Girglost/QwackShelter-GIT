package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOLieu;
import qwack_boot.model.Lieu;

@Service
public class LieuService {

	@Autowired
	IDAOLieu daoLieu;

	// --------------- CRUD ----------------

	public List<Lieu> getAll() {
		return daoLieu.findAll();
	}

	public Lieu getById(Integer id) {
		return daoLieu.findById(id).orElse(null);
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
