package qwack_boot.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.model.Canard;

@Service
public class CanardService {


     private final AnimalService animalService;


    CanardService(AnimalService animalService) {
        this.animalService = animalService;
    }


    public Canard insert(Canard canard) {

        return (Canard) animalService.insert(canard);
    }

    @Transactional
    public Canard update(Canard canard) {

        return (Canard) animalService.update(canard);
    }
}
