package qwack_boot.api.controller.animaux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.animal.UpdateAnimalRequest;
import qwack_boot.api.responseDTO.animal.PouleResponse;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Famille;
import qwack_boot.model.Poule;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.AnimalService;
import qwack_boot.service.PouleService;

@RestController
@RequestMapping("api/poule")
public class PouleRestController {

    private final PouleService srvPoule;

    private final IDAOQuackShelter daoQuackShelter;

    private final AnimalService animalService;

    PouleRestController(PouleService srvPoule, IDAOQuackShelter daoQuackShelter, AnimalService animalService) {
        this.srvPoule = srvPoule;
        this.daoQuackShelter = daoQuackShelter;
        this.animalService = animalService;
    }

    @PostMapping
    public PouleResponse ajouter(@RequestBody CreateAnimalRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        Poule poule = new Poule();

        poule.setNomAnimal(request.getNomAnimal());
        poule.setDateNaissance(request.getDateNaissance());
        poule.setCouleur(request.getCouleur());
        poule.setGenre(request.getGenre());
        poule.setCaracteres(request.getCaracteres());

        poule.setFamille(Famille.Galide);

        poule.setQuackShelter(refuge);

        poule.setPondeuse(request.isPondeuse());
        poule.setDescription(request.getDescription());

        return PouleResponse.convert(srvPoule.insert(poule));
    }

    @PutMapping("/{id}")
    public PouleResponse modifier(@PathVariable Integer id, @RequestBody UpdateAnimalRequest request) {

        Poule poule = (Poule) animalService.getById(id);

        if (poule == null) {
            throw new RuntimeException("Poule introuvable");
        }

        poule.setNomAnimal(request.getNomAnimal());
        poule.setCouleur(request.getCouleur());
        poule.setGenre(request.getGenre());
        poule.setCaracteres(request.getCaracteres());
        poule.setPondeuse(request.isPondeuse());
        poule.setDescription(request.getDescription());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            poule.setQuackShelter(refuge);
        }


        return PouleResponse.convert(srvPoule.update(poule));

    }
}
