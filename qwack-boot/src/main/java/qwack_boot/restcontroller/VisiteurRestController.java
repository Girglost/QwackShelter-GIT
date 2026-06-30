package qwack_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dto.personne.PersonneDTO;
import qwack_boot.dto.personne.VisiteurUpdateDTO;
import qwack_boot.model.Personne;
import qwack_boot.service.PersonneService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

    @Autowired
    PersonneService personneSrv;

    @GetMapping
    public List<PersonneDTO> chercherTous() {
        List<PersonneDTO> visiteurs = personneSrv.getAllVisiteur().stream()
                .map(visiteur -> PersonneDTO.convert(visiteur))
                .toList();
        ;
        return visiteurs;
    }

    @GetMapping("/{id}")
    public PersonneDTO chercherParId(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convert(personneSrv.getVisiteurById(id));
        return visiteur;
    }

    @GetMapping("/{id}/visites")
    public PersonneDTO chercherParIdWithVisites(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convertWithVisites(personneSrv.getVisiteurByIdWithVisites(id));
        return visiteur;
    }

    @GetMapping("/{id}/adoptions")
    public PersonneDTO chercherParIdWithAdoptions(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convertWithAdoptions(personneSrv.getVisiteurByIdWithAdoptions(id));
        return visiteur;
    }

    @PostMapping
    public PersonneDTO ajouterVisiteur(@RequestBody Personne personne) {

        PersonneDTO personneDTO = PersonneDTO.convert(personneSrv.insert(personne));
        return personneDTO;
    }

    @PutMapping("/{id}")
    public PersonneDTO modifierVisiteur(@PathVariable Integer id, @RequestBody VisiteurUpdateDTO visiteur) {
        Personne updatedPersonne = personneSrv.update(id, visiteur);
        PersonneDTO personneDTO = PersonneDTO.convert(updatedPersonne);
        return personneDTO;
    }

}
