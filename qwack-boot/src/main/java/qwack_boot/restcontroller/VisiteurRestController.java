package qwack_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.model.Personne;
import qwack_boot.service.PersonneService;

@RestController
@RequestMapping("/api/visiteur")
public class VisiteurRestController {

    @Autowired
    PersonneService personneSrv;

    @GetMapping
    public List<Personne> chercherTous() {
        List<Personne> visiteurs = personneSrv.getAll();
        return visiteurs;
    }

    @GetMapping("/{id}")
    public Personne chercherParId(@PathVariable Integer id) {
        System.out.println("On rentre ici !");
        Personne visiteur = personneSrv.getById(id);
        return visiteur;
    }

}
