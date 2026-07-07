package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreatePouleRequest;
import qwack_boot.api.requestDTO.animal.UpdatePouleRequest;
import qwack_boot.api.responseDTO.animal.PouleResponse;
import qwack_boot.model.Poule;
import qwack_boot.service.PouleService;

@RestController
@RequestMapping("api/poule")
public class PouleRestController {

    @Autowired
    private PouleService srvPoule;

    @PostMapping
    public PouleResponse ajouter(@RequestBody CreatePouleRequest request) {
        Poule poule = srvPoule.insert(request);
        return PouleResponse.convert(poule);
    }

    @PutMapping("/{id}")
    public PouleResponse modifier(@PathVariable Integer id, @RequestBody UpdatePouleRequest request) {
        Poule poule = srvPoule.update(id, request);

        return PouleResponse.convert(poule);
}
}
