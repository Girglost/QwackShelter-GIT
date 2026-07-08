package qwack_boot.service;

import org.springframework.stereotype.Service;

import qwack_boot.model.Chien;

@Service
public class ChienService {

    private final AnimalService animalService;

    ChienService(AnimalService animalService) {
        this.animalService = animalService;
    }


    public Chien insert(Chien chien) {

        /*
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
        */


        return (Chien) animalService.insert(chien);
    }

    public Chien update(Integer id, Chien chien) {

        // Chien chien = (Chien) animalService.getById(id);

        // if (chien == null) {
        //     throw new RuntimeException("Chien introuvable");
        // }

        // chien.setNomAnimal(request.getNomAnimal());
        // chien.setCouleur(request.getCouleur());
        // chien.setGenre(request.getGenre());
        // chien.setSterilisation(request.isSterilisation());
        // chien.setGestante(request.isGestante());
        // chien.setCaracteres(request.getCaracteres());
        // chien.setRace(request.getRace());

        // if (request.getQwackShelterId() != null) {
        //     QuackShelter refuge = daoQuackShelter
        //             .findById(request.getQwackShelterId())
        //             .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

        //     chien.setQuackShelter(refuge);
        // }
        chien.setId(id);
        return (Chien) animalService.update(chien);
    }


}
