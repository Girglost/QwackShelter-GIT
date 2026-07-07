package qwack_boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreatePouleRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Poule;

@RestController
@RequestMapping("api/poule")
public class PouleRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CreatePouleRequest ajouter(@RequestBody Poule poule) {
        return CreatePouleRequest.convert((Poule) daoAnimal.save(poule));
    }

    @PutMapping("/{id}")
    public CreatePouleRequest modifier(@PathVariable Integer id, @RequestBody Poule poule) {
        poule.setId(id);
        return CreatePouleRequest.convert((Poule) daoAnimal.save(poule));
    }
}
