package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.api.requestDTO.animal.CreatePouleRequest;
import qwack_boot.api.requestDTO.animal.UpdatePouleRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Poule;
import qwack_boot.model.QuackShelter;

@Service
public class PouleService {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Poule insert(CreatePouleRequest request) {

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

        return (Poule) animalService.insert(poule);
    }

    public Poule update(Integer id, UpdatePouleRequest request) {

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

        return (Poule) animalService.update(poule);
    }

}
