package qwack_boot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dao.IDAOAnimal;
import qwack_boot.dto.PouleDTO;
import qwack_boot.model.Poule;

@RestController
@RequestMapping("api/poule")
public class PouleRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public PouleDTO ajouter(@RequestBody Poule poule) {
        return PouleDTO.convert((Poule) daoAnimal.save(poule));
    }

    @PutMapping("/{id}")
    public PouleDTO modifier(@PathVariable Integer id, @RequestBody Poule poule) {
        poule.setId(id);
        return PouleDTO.convert((Poule) daoAnimal.save(poule));
    }
}
