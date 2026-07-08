package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.Poule;

@Service
public class PouleService {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public Poule insert(Poule poule) {

        return (Poule) animalService.insert(poule);
    }

    @Transactional
    public Poule update(Poule poule) {

        return (Poule) animalService.update(poule);
    }

}
