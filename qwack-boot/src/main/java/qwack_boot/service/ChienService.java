package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;

import qwack_boot.api.requestDTO.animal.CreateChienRequest;
import qwack_boot.api.requestDTO.animal.UpdateChienRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Chien;
import qwack_boot.model.QuackShelter;

public class ChienService {

     @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Chien insert(CreateChienRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        Chien chien = new Chien();

        chien.setNomAnimal(request.getNomAnimal());
        chien.setDateNaissance(request.getDateNaissance());
        chien.setCouleur(request.getCouleur());
        chien.setGenre(request.getGenre());
        chien.setSterilisation(request.isSterilisation());
        chien.setGestante(request.isGestante());
        chien.setCaracteres(request.getCaracteres());
        chien.setRace(request.getRace());

        chien.setQuackShelter(refuge);


        return (Chien) animalService.insert(chien);
    }

    public Chien update(Integer id, UpdateChienRequest request) {

        Chien chien = (Chien) animalService.getById(id);

        if (chien == null) {
            throw new RuntimeException("Chien introuvable");
        }

        chien.setNomAnimal(request.getNomAnimal());
        chien.setCouleur(request.getCouleur());
        chien.setGenre(request.getGenre());
        chien.setSterilisation(request.isSterilisation());
        chien.setGestante(request.isGestante());
        chien.setCaracteres(request.getCaracteres());
        chien.setRace(request.getRace());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            chien.setQuackShelter(refuge);
        }

        return (Chien) animalService.update(chien);
    }


}
