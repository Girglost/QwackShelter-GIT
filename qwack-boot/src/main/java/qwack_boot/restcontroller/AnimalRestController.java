package qwack_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dto.AnimalDTO;
import qwack_boot.model.Animal;
import qwack_boot.model.Genre;
import qwack_boot.model.Statut;
import qwack_boot.service.AnimalService;

@RestController
@RequestMapping("api/animal")
public class AnimalRestController {

	@Autowired
	AnimalService srvAnimal;

	@GetMapping
	public List<AnimalDTO> chercherTous() {
		return srvAnimal.getAll().stream().map(a -> AnimalDTO.convert(a)).toList();
	}

	@GetMapping("/{id}")
	public AnimalDTO chercherParId(@PathVariable Integer id) {
		Animal animal = srvAnimal.getById(id);
		return AnimalDTO.convert(animal);
	}

	@GetMapping("/{id}/historique-sante")
	public AnimalDTO chercherParIdAvecHistoriqueSante(@PathVariable Integer id) {
		return AnimalDTO.convertWithHistoriqueSante(srvAnimal.getByIdWithHistoriqueSante(id));
	}

	@GetMapping("/{id}/visites")
	public AnimalDTO chercherParIdAvecVisites(@PathVariable Integer id) {
		return AnimalDTO.convertWithVisites(srvAnimal.getByIdWithVisite(id));
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id) {
		srvAnimal.delete(id);
	}

	@GetMapping("/nom/{nom}")
	public List<AnimalDTO> chercherParNom(@PathVariable String nom) {
		return srvAnimal.getByName(nom).stream().map(a -> AnimalDTO.convert(a)).toList();
	}

	// Pour pouvoir filtrer les 3 en meme temps ou pas
	@GetMapping("/recherche")
	public List<AnimalDTO> rechercher(
			@RequestParam(required = false) String type,
			@RequestParam(required = false) Genre genre,
			@RequestParam(required = false) Statut statut) {
		return srvAnimal.rechercher(type, genre, statut)
				.stream().map(a -> AnimalDTO.convert(a)).toList();
	}

	@GetMapping("/disponible/{statut}")
	public List<AnimalDTO> chercherDisponibles(@PathVariable Statut statut) {
		return srvAnimal.getByStatut(statut).stream().map(a -> AnimalDTO.convert(a)).toList();
	}

	@GetMapping("/present")
	public List<AnimalDTO> chercherPresentRefuge() {
		return srvAnimal.getPresentAuRefugeWithCaracteres().stream().map(a -> AnimalDTO.convert(a)).toList();
	}

}
