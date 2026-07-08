package qwack_boot.api.controller.animaux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.animal.UpdateAnimalRequest;
import qwack_boot.api.responseDTO.animal.NACResponse;
import qwack_boot.dao.IDAOQuackShelter;
import qwack_boot.model.NAC;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.AnimalService;
import qwack_boot.service.NACService;

@RestController
@RequestMapping("api/nac")
public class NACRestController {

    private final NACService srvNAC;

    private final IDAOQuackShelter daoQuackShelter;

    private final AnimalService animalService;

    NACRestController(NACService srvNAC, IDAOQuackShelter daoQuackShelter, AnimalService animalService) {
        this.srvNAC = srvNAC;
        this.daoQuackShelter = daoQuackShelter;
        this.animalService = animalService;
    }

    @PostMapping
    public NACResponse ajouter(@RequestBody CreateAnimalRequest request) {

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

        nac.setFamille(request.getFamille());

        nac.setQuackShelter(refuge);

        srvNAC.insert(nac);
        return NACResponse.convert(nac);
    }

    @PutMapping("/{id}")
    public NACResponse modifier(@PathVariable Integer id, @RequestBody UpdateAnimalRequest request) {
        
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

              srvNAC.update(nac);

        return NACResponse.convert(nac);



    }

}
