package qwack_boot.api.controller.personne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.personne.CreateBenevoleRequest;
import qwack_boot.api.requestDTO.personne.UpdateBenevoleRequest;
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.model.Personne;
import qwack_boot.service.BenevoleService;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.StatutAnimalService;

@RestController
@RequestMapping("/api/benevole")
public class BenevoleRestController {

    @Autowired
    BenevoleService benevoleSrv;

    @Autowired
    PersonneService personneSrv;
    @Autowired
    QuackShelterService quackSrv;

    @Autowired
    StatutAnimalService statutAnimalSrv;

    @GetMapping
    public List<BenevoleResponse> chercherTous() {
        List<BenevoleResponse> visiteurs = benevoleSrv.getAllBenevole().stream()
                .map(visiteur -> BenevoleResponse.convert(visiteur))
                .toList();
        return visiteurs;
    }

    @GetMapping("/{id}")
    public BenevoleResponse chercherParId(@PathVariable Integer id) {
        BenevoleResponse benevole = BenevoleResponse.convert(benevoleSrv.getBenevoleById(id));
        return benevole;
    }

    @GetMapping("/{id}/adoptions")
    public BenevoleResponse chercherParIdWithAdoptions(@PathVariable Integer id) {
        BenevoleResponse benevole = BenevoleResponse.convertWithAdoptions(benevoleSrv.getBenevoleByIdWithAdoptions(id));
        return benevole;
    }

    @PostMapping
    public BenevoleResponse ajouterVisiteur(@RequestBody CreateBenevoleRequest benevoleRequest) {
        Personne benevole = benevoleSrv.insertBenevole(benevoleRequest);
        return BenevoleResponse.convert(benevole);
    }

    @PutMapping("/{id}")
    public BenevoleResponse modifierVisiteur(@PathVariable Integer id,
            @RequestBody UpdateBenevoleRequest benevoleRequest) {
        Personne benevole = benevoleSrv.updateBenevole(id, benevoleRequest);
        return BenevoleResponse.convert(benevole);
    }

    @DeleteMapping("/{id}")
    public BenevoleResponse deleteVisiteur(@PathVariable Integer id) {

        Personne deletedBenevole = benevoleSrv.getBenevoleById(id);
        personneSrv.deleteById(id);

        return BenevoleResponse.convert(deletedBenevole);
    }

}
