package qwack_boot.api.controller.personne;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.AdoptionRequest;
import qwack_boot.api.requestDTO.QuackShelterDTO;
import qwack_boot.api.requestDTO.VisiteDTO;
import qwack_boot.api.requestDTO.personne.ChangePasswordRequest;
import qwack_boot.api.requestDTO.personne.CreateVisiteurRequest;
import qwack_boot.api.requestDTO.personne.UpdateVisiteurRequest;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.api.responseDTO.personne.VisiteurResponse;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.EmplacementService;
import qwack_boot.service.LieuService;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.StatutAnimalService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

        private final PasswordEncoder passwordEncoder;
        private final EmplacementService emplacementService;
        @Autowired
        PersonneService personneSrv;

        @Autowired
        QuackShelterService quackSrv;
        @Autowired
        LieuService lieuSrv;

        @Autowired
        StatutAnimalService statutAnimalSrv;

        VisiteurRestController(EmplacementService emplacementService, PasswordEncoder passwordEncoder) {
                this.emplacementService = emplacementService;
                this.passwordEncoder = passwordEncoder;
        }

        @GetMapping
        public List<VisiteurResponse> chercherTous() {
                List<VisiteurResponse> visiteurs = personneSrv.getAllVisiteur().stream()
                                .map(visiteur -> VisiteurResponse.convert(visiteur))
                                .toList();
                return visiteurs;
        }

        @GetMapping("/{id}")
        public VisiteurResponse chercherParId(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse.convert(personneSrv.getVisiteurById(id));
                return visiteur;
        }

        @GetMapping("/{id}/visites")
        public VisiteurResponse chercherParIdWithVisites(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse
                                .convertWithVisites(personneSrv.getVisiteurByIdWithVisites(id));
                return visiteur;
        }

        @GetMapping("/{id}/adoptions")
        public VisiteurResponse chercherParIdWithAdoptions(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse
                                .convertWithAdoptions(personneSrv.getVisiteurByIdWithAdoptions(id));
                return visiteur;
        }

        @PostMapping
        public ResponseEntity<Map<String, VisiteurResponse>> ajouterVisiteur(
                        @RequestBody CreateVisiteurRequest visiteurRequest) {
                // On envoie un DTO, donc on reconstruit un objet Personne complet
                // pour l'envoyer au service !
                QuackShelter quackShelter = quackSrv.getById(visiteurRequest.getQuackShelterId());
                Lieu habitation = new Lieu();
                habitation.setType(visiteurRequest.getHabitation().getType());
                habitation.setAdresse(visiteurRequest.getHabitation().getAdresse());

                Personne visiteur = Personne.createVisiteur(visiteurRequest.getNom(), visiteurRequest.getPrenom(),
                                visiteurRequest.getLogin(), visiteurRequest.getPassword(), habitation,
                                quackShelter);

                VisiteurResponse visiteurCreated = VisiteurResponse.convert(personneSrv.insert(visiteur));
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(Map.of("Visiteur Créé", visiteurCreated));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Map<String, VisiteurResponse>> modifierVisiteur(@PathVariable Integer id,
                        @RequestBody UpdateVisiteurRequest visiteurRequest) {
                // On envoie un DTO, donc on reconstruit un objet Personne complet
                // pour l'envoyer au service !

                QuackShelter quackShelter = quackSrv.getById(visiteurRequest.getQuackShelterId());
                Lieu habitation = new Lieu();
                habitation.setType(visiteurRequest.getHabitation().getType());
                habitation.setAdresse(visiteurRequest.getHabitation().getAdresse());

                // On lui donne les champs qu'on a donné dans la updateRequest
                Personne visiteur = Personne.createVisiteur(visiteurRequest.getNom(), visiteurRequest.getPrenom(), null,
                                null, habitation,
                                quackShelter);
                // !!! ATTENTION, Ce n'est pas l'objet persister en base, on passe par le
                // service et c'est le service qui va faire le bon update !!
                System.out.println("CONTROLLER : visiteur recréé " + visiteur);
                VisiteurResponse visiteurUpdated = VisiteurResponse.convert(personneSrv.update(id, visiteur));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur Modifié", visiteurUpdated));
        }

        @PutMapping("/{id}/change-password")
        public ResponseEntity<Map<String, String>> changerMdp(@PathVariable Integer id,
                        @RequestBody ChangePasswordRequest passwordRequest) {
                Personne personne = personneSrv.getById(id);
                System.out.println("LOGIN EGAUX ???");
                System.out.println(personne.getLogin().equals(passwordRequest.getLogin()));
                System.out.println(passwordRequest.getLogin());

                if (!personne.getLogin().equals(passwordRequest.getLogin())) {
                        System.out.println("Login incorrect");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(Map.of("Login incorrect", passwordRequest.getLogin()));
                }

                String ancienPassword = passwordRequest.getAncienPassword();
                // On compare l'ancien password donner par le DTO et encodée, avec le passwrod
                // encodé en bdd
                if (passwordEncoder.matches(ancienPassword, personne.getPassword())) {
                        // On recupere le new password et on l'encode
                        personne.setPassword(passwordEncoder.encode(passwordRequest.getNouveauPassword()));
                        personneSrv.changePassword(personne);
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(Map.of("Password Modifié ! ", personne.getPassword()));

                } else {
                        System.out.println("MDP INCORRECT");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(Map.of("Ancien password incorrect", ancienPassword));
                }

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, VisiteurResponse>> deleteVisiteur(@PathVariable Integer id) {

                Personne deletedVisiteur = personneSrv.getVisiteurById(id);
                personneSrv.deleteById(id);

                VisiteurResponse benevoleDeleted = VisiteurResponse.convert(deletedVisiteur);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur DELETED", benevoleDeleted));
        }

        @PostMapping("/{id}/don")
        public ResponseEntity<Map<String, QuackShelterDTO>> faireUnDon(@PathVariable Integer id,
                        @RequestBody double don) {
                Personne visiteur = personneSrv.getVisiteurById(id);
                QuackShelter quackShelter = visiteur.getQuackShelter();
                personneSrv.faireDon(quackShelter.getId(), don);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Don au quackShelter", QuackShelterDTO.convert(quackShelter)));

        }

        @PostMapping("/{id}/engaged")
        public ResponseEntity<Map<String, BenevoleResponse>> devenirBenevole(@PathVariable Integer id) {
                Personne visiteur = personneSrv.getVisiteurById(id);
                BenevoleResponse visiteurDevientBenevole = BenevoleResponse
                                .convert(personneSrv.transformerEnBenevole(visiteur));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur devenu Benevole", visiteurDevientBenevole));
        }

        @PostMapping("{id}/visiter")
        public ResponseEntity<Map<String, VisiteDTO>> demanderVisite(@PathVariable Integer id,
                        @RequestBody VisiteDTO demandeVisite) {
                System.out.println("DEMANDE DE VISITE");
                int visiteurId = id;
                int quackShelterId = demandeVisite.getIdQuackShelter();
                int animalId = demandeVisite.getIdAnimal();
                LocalDateTime dateVisite = demandeVisite.getDateVisite();

                VisiteDTO visiteDemanded = VisiteDTO
                                .convert(personneSrv.demanderVisite(visiteurId, quackShelterId, dateVisite, animalId));

                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visite demandée", visiteDemanded));

        }

        @PostMapping("{id}/adopter")
        public ResponseEntity<Map<String, StatutAnimalReponse>> demanderAdoption(@PathVariable Integer id,
                        @RequestBody AdoptionRequest demandeAdoption) {
                System.out.println("DEMANDE D'ADOPTION");
                int visiteurId = id;
                int quackShelterId = demandeAdoption.getIdQuackShelter();
                int animalId = demandeAdoption.getIdAnimal();

                StatutAnimalReponse adoptionDemanded = StatutAnimalReponse
                                .convert(personneSrv.demanderAdoption(quackShelterId, visiteurId, animalId));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Adoption en attente", adoptionDemanded));
        }

}
