package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.dao.IDAOLieu;
import qwack_boot.dto.LieuUpdateDTO;
import qwack_boot.model.Adresse;
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

	public Lieu getByAdresse(Adresse adresse) {
		return daoLieu.findByAdresse(adresse);
	}

	// Dans les cas ou on va modifier une personne, on va chercher si le Lieu
	// existe, sinon créé un nouveau lieu
	@Transactional
	public Lieu findOrCreate(LieuUpdateDTO lieu) {

		Lieu existing = daoLieu.findByAdresse(lieu.getAdresse());

		if (existing != null) {
			return existing;
		}

		Lieu l = new Lieu(
				lieu.getType(),
				lieu.getAdresse().getNumero(),
				lieu.getAdresse().getVoie(),
				lieu.getAdresse().getVille(),
				lieu.getAdresse().getCp());

		return daoLieu.save(l);
	}

}
