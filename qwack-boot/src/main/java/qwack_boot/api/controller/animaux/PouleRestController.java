package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreatePouleRequest;
import qwack_boot.api.requestDTO.animal.CreatePouleRequest;
import qwack_boot.api.requestDTO.animal.UpdatePouleRequest;
import qwack_boot.api.requestDTO.animal.UpdatePouleRequest;
import qwack_boot.api.responseDTO.animal.PouleResponse;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.api.responseDTO.animal.PouleResponse;
import qwack_boot.model.Poule;
import qwack_boot.model.Poule;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.AnimalService;
import qwack_boot.service.PouleService;

@RestController
@RequestMapping("api/poule")
public class PouleRestController {

    @Autowired
    private PouleService srvPoule;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public PouleResponse ajouter(@RequestBody CreatePouleRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        Poule poule = new Poule();

        poule.setNomAnimal(request.getNomAnimal());
        poule.setDateNaissance(request.getDateNaissance());
        poule.setCouleur(request.getCouleur());
        poule.setGenre(request.getGenre());
        poule.setCaracteres(request.getCaracteres());

        poule.setQuackShelter(refuge);

        poule.setPondeuse(request.isPondeuse());

        srvPoule.insert(poule);
        return PouleResponse.convert(poule);
    }

    @PutMapping("/{id}")
    public PouleResponse modifier(@PathVariable Integer id, @RequestBody UpdatePouleRequest request) {

        Poule poule = (Poule) animalService.getById(id);

        if (poule == null) {
            throw new RuntimeException("Poule introuvable");
        }

        poule.setNomAnimal(request.getNomAnimal());
        poule.setCouleur(request.getCouleur());
        poule.setGenre(request.getGenre());
        poule.setCaracteres(request.getCaracteres());
        poule.setPondeuse(request.isPondeuse());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            poule.setQuackShelter(refuge);
        }

        srvPoule.update(poule);

        return PouleResponse.convert(poule);

    }
}
