package qwack_boot.api.controller.personne;

import java.time.LocalDateTime;
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
import qwack_boot.api.requestDTO.personne.CreateVisiteurRequest;
import qwack_boot.api.requestDTO.personne.UpdateVisiteurRequest;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.api.responseDTO.personne.VisiteurResponse;
import qwack_boot.dto.QuackShelterDTO;
import qwack_boot.dto.VisiteDTO;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.EmplacementService;
import qwack_boot.service.LieuService;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.StatutAnimalService;
import qwack_boot.service.VisiteurService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

        private final EmplacementService emplacementService;
        @Autowired
        PersonneService personneSrv;
        @Autowired
        VisiteurService visiteurSrv;
        @Autowired
        QuackShelterService quackSrv;
        @Autowired
        LieuService lieuSrv;

        @Autowired
        StatutAnimalService statutAnimalSrv;

        VisiteurRestController(EmplacementService emplacementService) {
                this.emplacementService = emplacementService;
        }

        @GetMapping
        public List<VisiteurResponse> chercherTous() {
                List<VisiteurResponse> visiteurs = visiteurSrv.getAllVisiteur().stream()
                                .map(visiteur -> VisiteurResponse.convert(visiteur))
                                .toList();
                return visiteurs;
        }

        @GetMapping("/{id}")
        public VisiteurResponse chercherParId(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse.convert(visiteurSrv.getVisiteurById(id));
                return visiteur;
        }

        @GetMapping("/{id}/visites")
        public VisiteurResponse chercherParIdWithVisites(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse
                                .convertWithVisites(visiteurSrv.getVisiteurByIdWithVisites(id));
                return visiteur;
        }

        @GetMapping("/{id}/adoptions")
        public VisiteurResponse chercherParIdWithAdoptions(@PathVariable Integer id) {
                VisiteurResponse visiteur = VisiteurResponse
                                .convertWithAdoptions(visiteurSrv.getVisiteurByIdWithAdoptions(id));
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

                VisiteurResponse visiteurCreated = VisiteurResponse.convert(visiteurSrv.insertVisiteur(visiteur));
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
                                visiteurRequest.getPassword(), habitation,
                                quackShelter);
                // !!! ATTENTION, Ce n'est pas l'objet persister en base, on passe par le
                // service et c'est le service qui va faire le bon update !!
                System.out.println("CONTROLLER : visiteur recréé " + visiteur);
                VisiteurResponse visiteurUpdated = VisiteurResponse.convert(visiteurSrv.updateVisiteur(id, visiteur));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur Modifié", visiteurUpdated));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, VisiteurResponse>> deleteVisiteur(@PathVariable Integer id) {

                Personne deletedVisiteur = visiteurSrv.getVisiteurById(id);
                personneSrv.deleteById(id);

                VisiteurResponse benevoleDeleted = VisiteurResponse.convert(deletedVisiteur);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur DELETED", benevoleDeleted));
        }

        @PostMapping("/{id}/don")
        public ResponseEntity<Map<String, QuackShelterDTO>> faireUnDon(@PathVariable Integer id,
                        @RequestBody double don) {
                Personne visiteur = visiteurSrv.getVisiteurById(id);
                QuackShelter quackShelter = visiteur.getQuackShelter();
                personneSrv.faireDon(quackShelter.getId(), don);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Don au quackShelter", QuackShelterDTO.convert(quackShelter)));

        }

        @PostMapping("/{id}/engaged")
        public ResponseEntity<Map<String, BenevoleResponse>> devenirBenevole(@PathVariable Integer id) {
                Personne visiteur = visiteurSrv.getVisiteurById(id);
                BenevoleResponse visiteurDevientBenevole = BenevoleResponse
                                .convert(visiteurSrv.transformerEnBenevole(visiteur));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visiteur devenu Benevole", visiteurDevientBenevole));
        }

        @PostMapping("/visiter")
        public ResponseEntity<Map<String, VisiteDTO>> demanderVisite(@RequestBody VisiteDTO demandeVisite) {
                System.out.println("DEMANDE DE VISITE");
                int visiteurId = demandeVisite.getIdVisiteur();
                int quackShelterId = demandeVisite.getIdQuackShelter();
                int animalId = demandeVisite.getIdAnimal();
                LocalDateTime dateVisite = demandeVisite.getDateVisite();

                VisiteDTO visiteDemanded = VisiteDTO
                                .convert(visiteurSrv.demanderVisite(visiteurId, quackShelterId, dateVisite, animalId));

                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visite demandée", visiteDemanded));

        }

        @PostMapping("/adopter")
        public ResponseEntity<Map<String, StatutAnimalReponse>> demanderAdoption(
                        @RequestBody AdoptionRequest demandeAdoption) {
                System.out.println("DEMANDE D'ADOPTION");
                int visiteurId = demandeAdoption.getIdPersonne();
                int quackShelterId = demandeAdoption.getIdQuackShelter();
                int animalId = demandeAdoption.getIdAnimal();

                StatutAnimalReponse adoptionDemanded = StatutAnimalReponse
                                .convert(visiteurSrv.demanderAdoption(quackShelterId, visiteurId, animalId));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Adoption en attente", adoptionDemanded));
        }

}
