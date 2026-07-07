package qwack_boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateChienRequest;
import qwack_boot.api.requestDTO.animal.UpdateChienRequest;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Chien;



@RestController
@RequestMapping("api/chien")
public class ChienRestController {
    
    @Autowired
    private IDAOAnimal daoAnimal;

    @PostMapping
    public CreateChienRequest ajouter(@RequestBody Chien chien) {
        return CreateChienRequest.convert((Chien) daoAnimal.save(chien));
    }

    @PutMapping("/{id}")
    public UpdateChienRequest modifier(@PathVariable Integer id, @RequestBody Chien chien) {
        chien.setId(id);
        return UpdateChienRequest.convert((Chien) daoAnimal.save(chien));
    }
}
