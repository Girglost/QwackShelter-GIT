package qwack_boot.api.controller.animaux;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.animal.UpdateAnimalRequest;
import qwack_boot.api.responseDTO.animal.ChienResponse;
import qwack_boot.model.Chien;
import qwack_boot.model.Famille;
import qwack_boot.service.ChienService;
import qwack_boot.service.QuackShelterService;

@RestController
@RequestMapping("api/chien")
public class ChienRestController {

    private final ChienService srvChien;
    private final QuackShelterService qSrv;

    ChienRestController(ChienService srvChien, QuackShelterService qSrv) {
        this.srvChien = srvChien;
        this.qSrv = qSrv;
    }

    @PostMapping
    public ChienResponse ajouter(@RequestBody CreateAnimalRequest request) {
        Chien chien = new Chien();

        chien.setNomAnimal(request.getNomAnimal());
        chien.setDateNaissance(request.getDateNaissance());
        chien.setCouleur(request.getCouleur());
        chien.setRegimeAlimentaire(request.getRegimeAlimentaire());
        chien.setTraitement(request.getTraitement());
        chien.setGenre(request.getGenre());
        chien.setFamille(Famille.Felin);
        chien.setSterilisation(request.isSterilisation());
        chien.setGestante(request.isGestante());
        chien.setRace(request.getRace());
        chien.setCaracteres(request.getCaracteres());
        chien.setQuackShelter(qSrv.getById(request.getQwackShelterId()));
        chien.setDescription(request.getDescription());

        srvChien.insert(chien);

        return ChienResponse.convert(chien);
    }

    @PutMapping("/{id}")
    public ChienResponse modifier(@PathVariable Integer id, @RequestBody UpdateAnimalRequest request) {
        Chien chien = new Chien();

        chien.setNomAnimal(request.getNomAnimal());
        chien.setDateNaissance(request.getDateNaissance());
        chien.setCouleur(request.getCouleur());
        chien.setRegimeAlimentaire(request.getRegimeAlimentaire());
        chien.setTraitement(request.getTraitement());
        chien.setGenre(request.getGenre());
        chien.setFamille(Famille.Felin);
        chien.setSterilisation(request.isSterilisation());
        chien.setGestante(request.isGestante());
        chien.setRace(request.getRace());
        chien.setCaracteres(request.getCaracteres());
        chien.setQuackShelter(qSrv.getById(request.getQwackShelterId()));
        chien.setDescription(request.getDescription());

        srvChien.update(id, chien);

        return ChienResponse.convert(chien);

    }
}
