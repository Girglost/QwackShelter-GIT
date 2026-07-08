package qwack_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qwack_boot.api.requestDTO.animal.CreateNACRequest;
import qwack_boot.api.requestDTO.animal.UpdateNACRequest;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.NAC;
import qwack_boot.model.QuackShelter;

@Service
public class NACService {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private IDAOQuackShelter daoQuackShelter;

    public NAC insert(NAC nac) {
        return (NAC) animalService.insert(nac);
    }

    public NAC update(NAC nac) {

        return (NAC) animalService.update(nac);
    }

}
