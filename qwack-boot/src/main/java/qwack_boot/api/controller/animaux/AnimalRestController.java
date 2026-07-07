package qwack_boot.api.controller.animaux;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.responseDTO.animal.AnimalResponse;
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
	public List<AnimalResponse> chercherTous() {
		return srvAnimal.getAll().stream().map(a -> AnimalResponse.convert(a)).toList();
	}

	@GetMapping("/{id}")
	public AnimalResponse chercherParId(@PathVariable Integer id) {
		Animal animal = srvAnimal.getById(id);
		return AnimalResponse.convert(animal);
	}

	@GetMapping("/{id}/historique-sante")
	public AnimalResponse chercherParIdAvecHistoriqueSante(@PathVariable Integer id) {
		return AnimalResponse.convertWithHistoriqueSante(srvAnimal.getByIdWithHistoriqueSante(id));
	}

	@GetMapping("/{id}/visites")
	public AnimalResponse chercherParIdAvecVisites(@PathVariable Integer id) {
		return AnimalResponse.convertWithVisites(srvAnimal.getByIdWithVisite(id));
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id) {
		srvAnimal.delete(id);
	}

	@GetMapping("/nom/{nom}")
	public List<AnimalResponse> chercherParNom(@PathVariable String nom) {
		return srvAnimal.getByName(nom).stream().map(a -> AnimalResponse.convert(a)).toList();
	}

	// Pour pouvoir filtrer les 3 en meme temps ou pas
	@GetMapping("/recherche")
	public List<AnimalResponse> rechercher(
			@RequestParam(required = false) String type,
			@RequestParam(required = false) Genre genre,
			@RequestParam(required = false) Statut statut) {
		return srvAnimal.rechercher(type, genre, statut)
				.stream().map(a -> AnimalResponse.convert(a)).toList();
	}

	@GetMapping("/disponible/{statut}")
	public List<AnimalResponse> chercherDisponibles(@PathVariable Statut statut) {
		return srvAnimal.getByStatut(statut).stream().map(a -> AnimalResponse.convert(a)).toList();
	}

	@GetMapping("/present")
	public List<AnimalResponse> chercherPresentRefuge() {
		return srvAnimal.getPresentAuRefugeWithCaracteres().stream().map(a -> AnimalResponse.convert(a)).toList();
	}

}
