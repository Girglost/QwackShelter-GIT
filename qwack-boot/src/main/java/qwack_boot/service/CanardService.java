package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;

import qwack_boot.api.requestDTO.animal.CreateCanardRequest;
import qwack_boot.api.requestDTO.animal.UpdateCanardRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Canard;
import qwack_boot.model.QuackShelter;

public class CanardService {


     @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Canard insert(CreateCanardRequest request) {

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

        return (Canard) animalService.insert(canard);
    }

    public Canard update(Integer id, UpdateCanardRequest request) {

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

        return (Canard) animalService.update(canard);
    }
}
