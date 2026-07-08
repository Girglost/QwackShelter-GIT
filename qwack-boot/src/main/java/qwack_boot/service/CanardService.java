package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.api.requestDTO.animal.CreateCanardRequest;
import qwack_boot.api.requestDTO.animal.UpdateCanardRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Canard;
import qwack_boot.model.QuackShelter;

@Service
public class CanardService {


     @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Canard insert(Canard canard) {

        return (Canard) animalService.insert(canard);
    }

    @Transactional
    public Canard update(Canard canard) {

        return (Canard) animalService.update(canard);
    }
}
