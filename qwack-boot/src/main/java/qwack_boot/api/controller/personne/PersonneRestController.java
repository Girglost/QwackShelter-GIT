package qwack_boot.api.controller.personne;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.responseDTO.personne.EmployeResponse;
import qwack_boot.api.responseDTO.personne.PersonneResponse;
import qwack_boot.api.responseDTO.personne.VisiteurResponse;
import qwack_boot.model.Personne;
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

    @GetMapping("/patron")
    public List<EmployeResponse> chercherPatroon() {
        List<EmployeResponse> patrons = personneSrv.getAllPatron().stream()
                .map(p -> EmployeResponse.convert(p))
                .toList();
        return patrons;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, VisiteurResponse>> delete(@PathVariable Integer id) {

        Personne deletedPersonne = personneSrv.getById(id);
        personneSrv.deleteById(id);

        VisiteurResponse personneDeleted = VisiteurResponse.convert(deletedPersonne);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Personne DELETED", personneDeleted));
    }

    // Permet de vérifier si le login envoyé exist
    // pour sécurisé l'insription
    @GetMapping("/check-login")
    public ResponseEntity<Boolean> checkLogin(
            @RequestParam String login) {
        return ResponseEntity.ok(personneSrv.loginExist(login));
    }

    @GetMapping("/profil")
    public PersonneResponse getMonProfil(Authentication authentication) {

        String login = authentication.getName();

        Personne personne = personneSrv.getProfilByLogin(login);

        return PersonneResponse.convertWithVisitesAndAdoptions(personne);
    }
}
