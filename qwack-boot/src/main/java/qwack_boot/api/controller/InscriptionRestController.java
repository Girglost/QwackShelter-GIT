package qwack_boot.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import qwack_boot.api.requestDTO.InscriptionRequest;
import qwack_boot.api.responseDTO.InscriptionResponse;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionRestController {
    private final PersonneService personneService;
    @Autowired
    QuackShelterService quackSrv;

    InscriptionRestController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, InscriptionResponse>> inscription(
            @Valid @RequestBody InscriptionRequest request) {
        // On peut s'inscrire uniquement en tant que visiteur ou benevole
        Personne personne = new Personne();

        QuackShelter quackShelter = quackSrv.getById(request.getQuackShelterId());
        Lieu habitation = new Lieu();
        habitation.setType(request.getHabitation().getType());
        habitation.setAdresse(request.getHabitation().getAdresse());

        switch (request.getRole()) {
            case VISITEUR:
                personne = Personne.createVisiteur(request.getNom(), request.getPrenom(),
                        request.getLogin(), request.getPassword(), habitation,
                        quackShelter);

                break;
            case BENEVOLE:
                personne = Personne.createBenevole(request.getNom(), request.getPrenom(),
                        request.getLogin(), request.getPassword(), habitation,
                        quackShelter);
                break;

            default:
                break;
        }

        // A partir de la request, on reconstruit l'utilisateur avant de d'appeler le
        // service

        personneService.insert(personne);
        InscriptionResponse subscribed = InscriptionResponse.convert(personne);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Inscription Réussi !", subscribed));
    }

}
