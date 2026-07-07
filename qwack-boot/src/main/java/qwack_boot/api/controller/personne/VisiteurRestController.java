package qwack_boot.api.controller.personne;

import java.time.LocalDateTime;
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

import qwack_boot.api.requestDTO.AdoptionRequest;
import qwack_boot.api.requestDTO.personne.CreateVisiteurRequest;
import qwack_boot.api.requestDTO.personne.UpdateVisiteurRequest;
import qwack_boot.api.responseDTO.personne.VisiteurResponse;
import qwack_boot.dto.QuackShelterDTO;
import qwack_boot.dto.VisiteDTO;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.PersonneService;
import qwack_boot.service.QuackShelterService;
import qwack_boot.service.VisiteurService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

    @Autowired
    PersonneService personneSrv;
    @Autowired
    VisiteurService visiteurSrv;
    @Autowired
    QuackShelterService quackSrv;

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
        VisiteurResponse visiteur = VisiteurResponse.convertWithVisites(visiteurSrv.getVisiteurByIdWithVisites(id));
        return visiteur;
    }

    @GetMapping("/{id}/adoptions")
    public VisiteurResponse chercherParIdWithAdoptions(@PathVariable Integer id) {
        VisiteurResponse visiteur = VisiteurResponse.convertWithAdoptions(visiteurSrv.getVisiteurByIdWithAdoptions(id));
        return visiteur;
    }

    @PostMapping
    public VisiteurResponse ajouterVisiteur(@RequestBody CreateVisiteurRequest visiteurRequest) {
        Personne visiteur = visiteurSrv.insertVisiteur(visiteurRequest);
        return VisiteurResponse.convert(visiteur);
    }

    @PutMapping("/{id}")
    public VisiteurResponse modifierVisiteur(@PathVariable Integer id,
            @RequestBody UpdateVisiteurRequest visiteurRequest) {
        Personne visiteur = visiteurSrv.updateVisiteur(id, visiteurRequest);
        System.out.println("APRES UPDATE ///////////////");
        System.out.println(visiteur);
        System.out.println("///////////////");
        return VisiteurResponse.convert(visiteur);
    }

    @DeleteMapping("/{id}")
    public VisiteurResponse deleteVisiteur(@PathVariable Integer id) {

        Personne deletedVisiteur = visiteurSrv.getVisiteurById(id);
        personneSrv.deleteById(id);

        return VisiteurResponse.convert(deletedVisiteur);
    }

    @PostMapping("/{id}/don")
    public QuackShelterDTO faireUnDon(@PathVariable Integer id, @RequestBody double don) {
        Personne visiteur = visiteurSrv.getVisiteurById(id);
        QuackShelter quackShelter = visiteur.getQuackShelter();
        personneSrv.faireDon(quackShelter.getId(), don);

        return QuackShelterDTO.convert(quackShelter);
    }

    @PostMapping("/{id}/engaged")
    public Personne devenirBenevole(@PathVariable Integer id) {
        Personne visiteur = visiteurSrv.getVisiteurById(id);
        visiteurSrv.transformerEnBenevole(visiteur);
        return visiteur;
    }

    @PostMapping("/visiter")
    public VisiteDTO demanderVisite(@RequestBody VisiteDTO demandeVisite) {
        System.out.println("DEMANDE DE VISITE");
        int visiteurId = demandeVisite.getIdVisiteur();
        int quackShelterId = demandeVisite.getIdQuackShelter();
        int animalId = demandeVisite.getIdAnimal();
        LocalDateTime dateVisite = demandeVisite.getDateVisite();
        return VisiteDTO.convert(visiteurSrv.demanderVisite(visiteurId, quackShelterId, dateVisite, animalId));
    }

    @PostMapping("/adopter")
    public String demanderAdoption(@RequestBody AdoptionRequest demandeAdoption) {
        System.out.println("DEMANDE D'ADOPTION");
        int visiteurId = demandeAdoption.getIdPersonne();
        int quackShelterId = demandeAdoption.getIdQuackShelter();
        int animalId = demandeAdoption.getIdAnimal();

        return " Demande d'adoption : " + animalId;
    }

}
