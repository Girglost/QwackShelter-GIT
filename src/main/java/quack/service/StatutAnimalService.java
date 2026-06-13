package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOStatutAnimal;
import quack.model.StatutAnimal;

@Service
public class StatutAnimalService {

	@Autowired
	IDAOStatutAnimal daoStatutAnimal;
	
	// --------------- CRUD ----------------
	
	public List<StatutAnimal> getAll(){
		return daoStatutAnimal.findAll();
	}
	public StatutAnimal getById(Integer id) {
		return  daoStatutAnimal.findById(id).orElse(null);
	}
	public void insert(StatutAnimal statut) {
		daoStatutAnimal.save(statut);
	}
	public void update(StatutAnimal statut) {
		daoStatutAnimal.save(statut);
	}
	public void delete(Integer id) {
		daoStatutAnimal.deleteById(id);
	}
	
	public List<StatutAnimal> getByAdoptant(Integer idPersonne){
		return daoStatutAnimal.findByAdoptant(idPersonne);
	}

}
