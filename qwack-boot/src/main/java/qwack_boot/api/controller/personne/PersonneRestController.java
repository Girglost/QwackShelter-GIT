package qwack_boot.api.controller.personne;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.responseDTO.personne.VisiteurResponse;
import qwack_boot.model.StatutActivite;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.StatutAnimalService;

@RestController
@RequestMapping("/api/personne")
public class PersonneRestController {

    private PasswordEncoder passwordEncoder;
    @Autowired
    PersonneService personneSrv;
    @Autowired
    QuackShelterService quackSrv;

    @Autowired
    StatutAnimalService statutAnimalSrv;

    @GetMapping
    public List<VisiteurResponse> chercherTous() {
        List<VisiteurResponse> personnes = personneSrv.getAll().stream()
                .map(p -> VisiteurResponse.convert(p))
                .toList();
        return personnes;
    }

    @GetMapping("/{id}")
    public VisiteurResponse chercherParId(@PathVariable Integer id) {
        VisiteurResponse personne = VisiteurResponse.convert(personneSrv.getById(id));
        return personne;
    }

    @GetMapping("/quackshelter/{id}")
    public List<VisiteurResponse> chercherParQuackShelter(@PathVariable Integer id) {
        List<VisiteurResponse> personnes = personneSrv.getByQuackShelterId(id).stream()
                .map(p -> VisiteurResponse.convert(p))
                .toList();
        return personnes;
    }

    @GetMapping("/statut/{statut}")
    public List<VisiteurResponse> chercherParStatutActivite(@PathVariable StatutActivite statut) {
        List<VisiteurResponse> personnes = personneSrv.getByStatutActivite(statut).stream()
                .map(p -> VisiteurResponse.convert(p))
                .toList();
        return personnes;
    }

    @GetMapping("/admin")
    public List<VisiteurResponse> chercherParAdmin() {
        List<VisiteurResponse> personnes = personneSrv.getByAdmin(true).stream()
                .map(p -> VisiteurResponse.convert(p))
                .toList();
        return personnes;
    }

}
