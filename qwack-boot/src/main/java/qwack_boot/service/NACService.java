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

    public NAC insert(CreateNACRequest request) {

        QuackShelter refuge = daoQuackShelter
                .findById(request.getQwackShelterId())
                .orElseThrow();

        NAC nac = new NAC();

        nac.setNomAnimal(request.getNomAnimal());
        nac.setDateNaissance(request.getDateNaissance());
        nac.setCouleur(request.getCouleur());
        nac.setGenre(request.getGenre());
        nac.setSterilisation(request.isSterilisation());
        nac.setGestante(request.isGestante());
        nac.setCaracteres(request.getCaracteres());
        nac.setEspece(request.getEspece());

        nac.setQuackShelter(refuge);

        return (NAC) animalService.insert(nac);
    }

    public NAC update(Integer id, UpdateNACRequest request) {

        NAC nac = (NAC) animalService.getById(id);

        if (nac == null) {
            throw new RuntimeException("NAC introuvable");
        }

        nac.setNomAnimal(request.getNomAnimal());
        nac.setCouleur(request.getCouleur());
        nac.setGenre(request.getGenre());
        nac.setSterilisation(request.isSterilisation());
        nac.setGestante(request.isGestante());
        nac.setCaracteres(request.getCaracteres());
        nac.setEspece(request.getEspece());

        if (request.getQwackShelterId() != null) {
            QuackShelter refuge = daoQuackShelter
                    .findById(request.getQwackShelterId())
                    .orElseThrow(() -> new RuntimeException("Refuge introuvable"));

            nac.setQuackShelter(refuge);
        }

        return (NAC) animalService.update(nac);
    }

}
