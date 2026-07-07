package qwack_boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateCanardRequest;
import qwack_boot.api.requestDTO.animal.UpdateCanardRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Canard;

@RestController
@RequestMapping("api/canard")
public class CanardRestController {

    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CreateCanardRequest ajouter(@RequestBody Canard canard) {
        return CreateCanardRequest.convert((Canard) daoAnimal.save(canard));
    }

    @PutMapping("/{id}")
    public UpdateCanardRequest modifier(@PathVariable Integer id, @RequestBody Canard canard) {
        canard.setId(id);
        return UpdateCanardRequest.convert((Canard) daoAnimal.save(canard));
    }

}
