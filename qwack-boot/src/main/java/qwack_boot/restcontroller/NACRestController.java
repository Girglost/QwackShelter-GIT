package qwack_boot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.dao.IDAOAnimal;
import qwack_boot.dto.NACDTO;
import qwack_boot.model.NAC;

@RestController
@RequestMapping("api/nac")
public class NACRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public NACDTO ajouter(@RequestBody NAC nac) {
        return NACDTO.convert((NAC) daoAnimal.save(nac));
    }

    @PutMapping("/{id}")
    public NACDTO modifier(@PathVariable Integer id, @RequestBody NAC nac) {
        nac.setId(id);
        return NACDTO.convert((NAC) daoAnimal.save(nac));
    }

}
