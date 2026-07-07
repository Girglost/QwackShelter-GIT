package qwack_boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateNACRequest;
import qwack_boot.api.requestDTO.animal.UpdateNACRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.NAC;

@RestController
@RequestMapping("api/nac")
public class NACRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CreateNACRequest ajouter(@RequestBody NAC nac) {
        return CreateNACRequest.convert((NAC) daoAnimal.save(nac));
    }

    @PutMapping("/{id}")
    public UpdateNACRequest modifier(@PathVariable Integer id, @RequestBody NAC nac) {
        nac.setId(id);
        return UpdateNACRequest.convert((NAC) daoAnimal.save(nac));
    }

}
