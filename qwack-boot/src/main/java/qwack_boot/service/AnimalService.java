package qwack_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.api.requestDTO.animal.CreateChatRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Animal;
import qwack_boot.model.Canard;
import qwack_boot.model.Chat;
import qwack_boot.model.Chien;
import qwack_boot.model.Genre;
import qwack_boot.model.NAC;
import qwack_boot.model.Poule;
import qwack_boot.model.Statut;

@Service
public class AnimalService {

	@Autowired
	IDAOAnimal daoAnimal;

	@Autowired
	IDAOQuackShelter daoQuackShelter;

	// --------------- CRUD ----------------

	public List<Animal> getAll() {
		return daoAnimal.findAll();
	}

	public Animal getById(Integer id) {
		return daoAnimal.findById(id).orElse(null);
	}

	public Animal insert(Animal animal) {
		return daoAnimal.save(animal);
	}

	public Animal update(Animal animal) {
		return daoAnimal.save(animal);
	}

	public void delete(Integer id) {
		daoAnimal.deleteById(id);
	}

	// --------------- PERSO ----------------

	public List<Animal> getByName(String name) {
		return daoAnimal.findByNomAnimal(name);
	}

	public List<Animal> getByStatut(Statut statut) {
		return daoAnimal.findByStatut(statut);
	}

	public List<Animal> getByGenre(Genre genre) {
		return daoAnimal.findByGenre(genre);
	}

	// on indique un string sur le front et on traduit ça pour renvoyer la bonne
	// classe qui correspond

	public List<Animal> rechercher(String type, Genre genre, Statut statut) {
		Class<? extends Animal> classe = (type != null) ? resoudreClasse(type) : null;
		return daoAnimal.rechercher(classe, genre, statut);
	}

	private Class<? extends Animal> resoudreClasse(String type) {
		switch (type.toLowerCase()) {
			case "chat":
				return Chat.class;
			case "chien":
				return Chien.class;
			case "canard":
				return Canard.class;
			case "nac":
				return NAC.class;
			case "poule":
				return Poule.class;
			default:
				throw new IllegalArgumentException("Type d'animal inconnu : " + type);
		}
	}

	public List<Animal> getPresentAuRefugeWithCaracteres() {
		return daoAnimal.PresentsAuRefugeWithCaracteres();
	}

	public Animal getByIdWithHistoriqueSante(Integer id) {
		return daoAnimal.findByIdWithHistoriqueSante(id);
	}

	public Animal getByIdWithVisite(int idAnimal) {
		return daoAnimal.findByIdWithVisite(idAnimal);
	}

}
