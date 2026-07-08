package qwack_boot.api.controller.animaux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.animal.UpdateAnimalRequest;
import qwack_boot.api.responseDTO.animal.CanardResponse;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Canard;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.AnimalService;
import qwack_boot.service.CanardService;

@RestController
@RequestMapping("api/canard")
public class CanardRestController {

    private final CanardService srvCanard;

    private final IDAOQuackShelter daoQuackShelter;

    private final AnimalService animalService;

    CanardRestController(CanardService srvCanard, IDAOQuackShelter daoQuackShelter, AnimalService animalService) {
        this.srvCanard = srvCanard;
        this.daoQuackShelter = daoQuackShelter;
        this.animalService = animalService;
    }

    @PostMapping
    public CanardResponse ajouter(@RequestBody CreateAnimalRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        Canard canard = new Canard();

        canard.setNomAnimal(request.getNomAnimal());
        canard.setDateNaissance(request.getDateNaissance());
        canard.setCouleur(request.getCouleur());
        canard.setGenre(request.getGenre());
        canard.setCaracteres(request.getCaracteres());
        canard.setestSauvage(request.isEstSauvage());

        canard.setQuackShelter(refuge);

        canard.setPondeuse(request.isPondeuse());

        srvCanard.insert(canard);
        return CanardResponse.convert(canard);
    }

    @PutMapping("/{id}")
    public CanardResponse modifier(@PathVariable Integer id, @RequestBody UpdateAnimalRequest request) {

        Canard canard = (Canard) animalService.getById(id);

        if (canard == null) {
            throw new RuntimeException("Canard introuvable");
        }

        canard.setNomAnimal(request.getNomAnimal());
        canard.setCouleur(request.getCouleur());
        canard.setGenre(request.getGenre());
        canard.setCaracteres(request.getCaracteres());
        canard.setPondeuse(request.isPondeuse());
        canard.setestSauvage(request.isEstSauvage());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            canard.setQuackShelter(refuge);
        }

        srvCanard.update(canard);

        return CanardResponse.convert(canard);

    }
}
