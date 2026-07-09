package qwack_boot.service;

import org.springframework.stereotype.Service;

import qwack_boot.model.NAC;

@Service
public class NACService {

    private final AnimalService animalService;


    NACService(AnimalService animalService) {
        this.animalService = animalService;
    }

    public NAC insert(NAC nac) {
        return (NAC) animalService.insert(nac);
    }

    public NAC update(NAC nac) {

        return (NAC) animalService.update(nac);
    }

}
