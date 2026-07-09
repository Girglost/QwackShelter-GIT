package qwack_boot.api.controller.personne;

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
import qwack_boot.api.requestDTO.CreateHistoriqueSanteRequest;
import qwack_boot.api.requestDTO.animal.CreateAnimalRequest;
import qwack_boot.api.requestDTO.personne.ChangePasswordRequest;
import qwack_boot.api.requestDTO.personne.CreateEmployeRequest;
import qwack_boot.api.requestDTO.personne.UpdateEmployeRequest;
import qwack_boot.api.responseDTO.HistoriqueSanteReponse;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.personne.EmployeResponse;
import qwack_boot.dto.VisiteDTO;
import qwack_boot.model.Chat;
import qwack_boot.model.HistoriqueSante;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.AnimalService;
import qwack_boot.service.EmplacementService;
import qwack_boot.service.EmployeService;
import qwack_boot.service.HistoriqueSanteService;
import qwack_boot.service.LieuService;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.StatutAnimalService;
import qwack_boot.service.VisiteurService;

@RestController
@RequestMapping("/api/employe")
public class EmployeRestController {

        private final EmplacementService emplacementService;
        private final PasswordEncoder passwordEncoder;
        @Autowired
        PersonneService personneSrv;
        @Autowired
        VisiteurService visiteurSrv;
        @Autowired
        EmployeService employeSrv;
        @Autowired
        QuackShelterService quackSrv;
        @Autowired
        LieuService lieuSrv;
        @Autowired
        AnimalService animalSrv;

        @Autowired
        StatutAnimalService statutAnimalSrv;

        @Autowired
        HistoriqueSanteService historiqueSanteSrv;

        EmployeRestController(EmplacementService emplacementService, PasswordEncoder passwordEncoder) {
                this.emplacementService = emplacementService;
                this.passwordEncoder = passwordEncoder;
        }

        @GetMapping
        public List<EmployeResponse> chercherTous() {
                List<EmployeResponse> employes = employeSrv.getAllEmploye().stream()
                                .map(employe -> EmployeResponse.convert(employe))
                                .toList();
                return employes;
        }

        @GetMapping("/{id}")
        public EmployeResponse chercherParId(@PathVariable Integer id) {
                EmployeResponse employe = EmployeResponse.convert(employeSrv.getEmployeById(id));
                return employe;
        }

        @PostMapping
        public ResponseEntity<Map<String, EmployeResponse>> ajouterEmploye(
                        @RequestBody CreateEmployeRequest employeRequest) {
                // On envoie un DTO, donc on reconstruit un objet Personne complet
                // pour l'envoyer au service !
                QuackShelter quackShelter = quackSrv.getById(employeRequest.getQuackShelterId());
                Lieu habitation = new Lieu();
                habitation.setType(employeRequest.getHabitation().getType());
                habitation.setAdresse(employeRequest.getHabitation().getAdresse());

                Personne employe = Personne.createEmploye(employeRequest.getNom(), employeRequest.getPrenom(),
                                employeRequest.getLogin(), employeRequest.getPassword(), habitation,
                                quackShelter, employeRequest.isAdmin(), employeRequest.getSalaire());

                EmployeResponse employeCreated = EmployeResponse.convert(employeSrv.insertEmploye(employe));
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(Map.of("EMPLOYE Créé", employeCreated));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Map<String, EmployeResponse>> modifierEmploye(@PathVariable Integer id,
                        @RequestBody UpdateEmployeRequest employeRequest) {
                // On envoie un DTO, donc on reconstruit un objet Personne complet
                // pour l'envoyer au service !

                QuackShelter quackShelter = quackSrv.getById(employeRequest.getQuackShelterId());
                Lieu habitation = new Lieu();
                habitation.setType(employeRequest.getHabitation().getType());
                habitation.setAdresse(employeRequest.getHabitation().getAdresse());

                // On lui donne les champs qu'on a donné dans la updateRequest
                Personne employe = Personne.createEmploye(employeRequest.getNom(), employeRequest.getPrenom(), null,
                                null, habitation,
                                quackShelter, employeRequest.isAdmin(), employeRequest.getSalaire());

                employe.setStatutActivite(employeRequest.getStatutActivite());
                // !!! ATTENTION, Ce n'est pas l'objet persister en base, on passe par le
                // service et c'est le service qui va faire le bon update !!
                System.out.println("CONTROLLER : Employe recréé " + employe);
                EmployeResponse employeUpdated = EmployeResponse.convert(employeSrv.updateEmploye(id, employe));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("EMPLOYE Modifié", employeUpdated));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, EmployeResponse>> deleteEmploye(@PathVariable Integer id) {

                Personne deletedEmploye = employeSrv.getEmployeById(id);
                personneSrv.deleteById(id);

                EmployeResponse employeDeleted = EmployeResponse.convert(deletedEmploye);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Employe DELETED", employeDeleted));
        }

        @PutMapping("/{id}/change-password")
        public ResponseEntity<Map<String, String>> changerMdp(@PathVariable Integer id,
                        @RequestBody ChangePasswordRequest passwordRequest) {
                Personne personne = personneSrv.getById(id);

                String ancienPassword = passwordRequest.getAncienPassword();
                // On compare l'ancien password donner par le DTO et encodée, avec le passwrod
                // encodé en bdd
                if (passwordEncoder.encode(ancienPassword) == personne.getPassword()) {
                        // On recupere le new password et on l'encode
                        personne.setPassword(passwordEncoder.encode(passwordRequest.getNouveauPassword()));
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(Map.of("Password Modifié ! ", personne.getPassword()));

                } else {
                        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                                        .body(Map.of("Ancien password incorrect", ancienPassword));
                }

        }

        @PutMapping("/accepter-adoption/{id}")
        public ResponseEntity<Map<String, StatutAnimalReponse>> accepterAdoption(
                        @RequestBody AdoptionRequest adoptionRequest,
                        @PathVariable Integer id) {
                int idAdoptant = adoptionRequest.getIdPersonne();
                int idAdoption = id;
                int idAnimal = adoptionRequest.getIdAnimal();

                StatutAnimalReponse adoptionAccepted = StatutAnimalReponse
                                .convert(employeSrv.accepterAdoption(idAdoptant, idAdoption, idAnimal));

                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Adoption ACCEPTE", adoptionAccepted));
        }

        @PutMapping("/accepter-visite/{id}")
        public ResponseEntity<Map<String, VisiteDTO>> accepterVisite(
                        @PathVariable Integer id) {
                VisiteDTO visiteAccepted = VisiteDTO
                                .convert(employeSrv.accepterVisite(id));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Visite ACCEPTE", visiteAccepted));
        }

        @PostMapping("/soin/{id}")
        public ResponseEntity<Map<String, HistoriqueSanteReponse>> partirEnSoin(
                        @RequestBody CreateHistoriqueSanteRequest soinRequest,
                        @PathVariable("id") Integer idEmploye) {

                // On reconstruit l'objet pour l'envoyer au service
                HistoriqueSante soin = new HistoriqueSante(
                                soinRequest.commentaire(),
                                soinRequest.poids(),
                                soinRequest.cause(),
                                animalSrv.getById(soinRequest.animalId()));

                HistoriqueSanteReponse soinEffectue = HistoriqueSanteReponse
                                .convert(employeSrv.partirEnSoin(idEmploye, soin));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Soin effectué", soinEffectue));
        }

        @PostMapping("/accueillir-animal")
        public ResponseEntity<Map<String, String>> accueillirAnimal(
                        @RequestBody CreateAnimalRequest animalRequest) {
                Chat chat = new Chat();
                String typeAnimal = chat.getClass().getSimpleName();

                // A partir du dto de l'animal, on reconstruit un objet pour l'envoyer au
                // service
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("Animal accueillit ", typeAnimal));
        }

}
