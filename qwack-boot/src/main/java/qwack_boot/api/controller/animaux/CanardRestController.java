package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateCanardRequest;
import qwack_boot.api.requestDTO.animal.UpdateCanardRequest;
import qwack_boot.api.responseDTO.animal.CanardResponse;
import qwack_boot.dao.IDAOAnimal;
import qwack_boot.model.Canard;
import qwack_boot.service.CanardService;

@RestController
@RequestMapping("api/canard")
public class CanardRestController {


    @Autowired
    private CanardService srvCanard;

    @PostMapping
    public CanardResponse ajouter(@RequestBody CreateCanardRequest request) {
        Canard canard = srvCanard.insert(request);
        return CanardResponse.convert(canard);
    }

    @PutMapping("/{id}")
    public CanardResponse modifier(@PathVariable Integer id, @RequestBody UpdateCanardRequest request) {
        Canard canard = srvCanard.update(id, request);

        return CanardResponse.convert(canard);

}
}
