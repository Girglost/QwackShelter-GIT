package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;
import qwack_boot.api.requestDTO.lieu.UpdateLieuRequest;
import qwack_boot.dao.IDAOLieu;
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

	public Lieu insert(CreateLieuRequest lieuRequest) {
		Lieu lieu = new Lieu();
		lieu.setType(lieuRequest.getType());
		lieu.setAdresse(lieuRequest.getAdresse());
		return daoLieu.save(lieu);
	}

	public void update(Integer id, UpdateLieuRequest request) {
		Lieu lieu = daoLieu.findById(id)
				.orElseThrow(() -> new RuntimeException("Lieu introuvable"));

		lieu.setType(request.getType());
		lieu.setAdresse(request.getAdresse());

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
	public Lieu findOrCreate(Lieu lieu) {

		System.out.println("RECHERCHE DU LIEU ////////////");
		System.out.println(lieu.getAdresse());
		System.out.println("FIND BY ADRESSE ???");
		Lieu existing = daoLieu.findByAdresse(lieu.getAdresse());
		System.out.println("EXISTING ?? " + existing);
		if (existing != null) {
			System.out.println("LIEU TROUVE : " + existing);
			return existing;
		}
		System.out.println("LIEU INEXISTANT creation du lieu : ");
		Lieu l = new Lieu(
				lieu.getType(),
				lieu.getAdresse().getNumero(),
				lieu.getAdresse().getVoie(),
				lieu.getAdresse().getVille(),
				lieu.getAdresse().getCp());

		Lieu persistLieu = daoLieu.save(l);
		System.out.println(l.getId());
		return persistLieu;
	}

}
