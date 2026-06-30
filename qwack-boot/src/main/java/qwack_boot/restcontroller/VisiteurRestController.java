package qwack_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dto.PersonneDTO;
import qwack_boot.service.PersonneService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

    @Autowired
    PersonneService personneSrv;

    @GetMapping
    public List<PersonneDTO> chercherTous() {
        List<PersonneDTO> visiteurs = personneSrv.getAll().stream().map(visiteur -> PersonneDTO.convert(visiteur))
                .toList();
        ;
        return visiteurs;
    }

    @GetMapping("/{id}")
    public PersonneDTO chercherParId(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convert(personneSrv.getById(id));
        return visiteur;
    }

    @GetMapping("/{id}/visites")
    public PersonneDTO chercherParIdWithVisites(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convertWithVisites(personneSrv.getById(id));
        return visiteur;
    }

    @GetMapping("/{id}/adoptions")
    public PersonneDTO chercherParIdWithAdoptions(@PathVariable Integer id) {
        PersonneDTO visiteur = PersonneDTO.convertWithAdoptions(personneSrv.getById(id));
        return visiteur;
    }

}
