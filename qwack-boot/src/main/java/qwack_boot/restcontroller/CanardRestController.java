package qwack_boot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dao.IDAOAnimal;
import qwack_boot.dto.CanardDTO;
import qwack_boot.model.Canard;

@RestController
@RequestMapping("api/canard")
public class CanardRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CanardDTO ajouter(@RequestBody Canard canard) {
        return CanardDTO.convert((Canard) daoAnimal.save(canard));
    }

    @PutMapping("/{id}")
    public CanardDTO modifier(@PathVariable Integer id, @RequestBody Canard canard) {
        canard.setId(id);
        return CanardDTO.convert((Canard) daoAnimal.save(canard));
    }

}
