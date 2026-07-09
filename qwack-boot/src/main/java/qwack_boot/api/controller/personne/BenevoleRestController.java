package qwack_boot.api.controller.personne;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.AdoptionRequest;
import qwack_boot.api.requestDTO.personne.CreateBenevoleRequest;
import qwack_boot.api.requestDTO.personne.UpdateBenevoleRequest;
import qwack_boot.api.requestDTO.statutAnimal.CreateStatutAnimalRequest;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.dto.QuackShelterDTO;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.StatutAnimal;
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
    public ResponseEntity<Map<String, BenevoleResponse>> ajouterBenevole(
            @RequestBody CreateBenevoleRequest benevoleRequest) {
        // On envoie un DTO, donc on reconstruit un objet Personne complet
        // pour l'envoyer au service !

        System.out.println("ON VA RECUP QS");

        System.out.println(benevoleRequest);
        QuackShelter quackShelter = quackSrv.getById(benevoleRequest.getQuackShelterId());
        Lieu habitation = new Lieu();
        habitation.setType(benevoleRequest.getHabitation().getType());
        habitation.setAdresse(benevoleRequest.getHabitation().getAdresse());

        Personne benevole = Personne.createBenevole(benevoleRequest.getNom(), benevoleRequest.getPrenom(),
                benevoleRequest.getLogin(), benevoleRequest.getPassword(), habitation,
                quackShelter);
        System.out.println("BENEVOLE QUI VA ETRE INSERT " + benevole);

        BenevoleResponse benevoleCreated = BenevoleResponse.convert(benevoleSrv.insertBenevole(benevole));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Benevole Créé", benevoleCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, BenevoleResponse>> modifierBenevole(@PathVariable Integer id,
            @RequestBody UpdateBenevoleRequest benevoleRequest) {

        // On envoie un DTO, donc on reconstruit un objet Personne complet
        // pour l'envoyer au service !

        QuackShelter quackShelter = quackSrv.getById(benevoleRequest.getQuackShelterId());
        Lieu habitation = new Lieu();
        habitation.setType(benevoleRequest.getHabitation().getType());
        habitation.setAdresse(benevoleRequest.getHabitation().getAdresse());

        // On lui donne les champs qu'on a donné dans la updateRequest
        Personne benevole = Personne.createBenevole(benevoleRequest.getNom(), benevoleRequest.getPrenom(), null,
                benevoleRequest.getPassword(), habitation,
                quackShelter);
        // !!! ATTENTION, Ce n'est pas l'objet persister en base, on passe par le
        // service et c'est le service qui va faire le bon update !!
        benevole.setNom(benevoleRequest.getNom());
        benevole.setPrenom(benevoleRequest.getPrenom());
        benevole.setHabitation(habitation);
        benevole.setPassword(benevoleRequest.getPassword());
        System.out.println("CONTROLLER : benevole recréé " + benevole);

        BenevoleResponse benevoleUpdated = BenevoleResponse.convert(benevoleSrv.updateBenevole(id, benevole));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Benevole Modifié", benevoleUpdated));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, BenevoleResponse>> deleteBenevole(@PathVariable Integer id) {

        Personne deletedBenevole = benevoleSrv.getBenevoleById(id);
        personneSrv.deleteById(id);

        BenevoleResponse benevoleDeleted = BenevoleResponse.convert(deletedBenevole);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Benevole Supprimé", benevoleDeleted));
    }

    @PutMapping("/{id}/balade")
    public ResponseEntity<Map<String, BenevoleResponse>> partirEnBalade(@PathVariable Integer id,
            @RequestBody CreateStatutAnimalRequest statutAnimalRequest) {
        System.out.println("BALADER CONTROLER");
        Personne benevole = benevoleSrv.getBenevoleById(id);
        if (benevole != null) {
            System.out.println(benevole);
            StatutAnimal statutAnimal = statutAnimalSrv.getByAnimalId(statutAnimalRequest.animalId());
            BenevoleResponse benevoleEnBalade = benevoleSrv.partirEnBalade(statutAnimal, benevole);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("Benevole en balade", benevoleEnBalade));
        } else {
            BenevoleResponse erreurBenevole = BenevoleResponse.convert(benevole);
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(Map.of("RESERVE AUX BENEVOLES", erreurBenevole));
        }
    }

    @PostMapping("/{id}/don")
    public ResponseEntity<Map<String, QuackShelterDTO>> faireUnDon(@PathVariable Integer id, @RequestBody double don) {
        Personne benevole = benevoleSrv.getBenevoleById(id);
        QuackShelter quackShelter = benevole.getQuackShelter();
        personneSrv.faireDon(quackShelter.getId(), don);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Don au quackShelter", QuackShelterDTO.convert(quackShelter)));

    }

    @PostMapping("/adopter")
    public ResponseEntity<Map<String, StatutAnimalReponse>> demanderAdoption(
            @RequestBody AdoptionRequest demandeAdoption) {
        System.out.println("DEMANDE D'ADOPTION");
        int benevoleId = demandeAdoption.getIdPersonne();
        int quackShelterId = demandeAdoption.getIdQuackShelter();
        int animalId = demandeAdoption.getIdAnimal();

        StatutAnimalReponse adoptionDemanded = StatutAnimalReponse
                .convert(benevoleSrv.demanderAdoption(quackShelterId, benevoleId, animalId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Adoption en attente", adoptionDemanded));
    }

}
