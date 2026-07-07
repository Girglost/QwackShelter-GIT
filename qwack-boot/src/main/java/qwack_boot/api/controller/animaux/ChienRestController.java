package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateChienRequest;
import qwack_boot.api.requestDTO.animal.UpdateChienRequest;
import qwack_boot.api.responseDTO.animal.ChienResponse;
import qwack_boot.model.Chien;
import qwack_boot.service.ChienService;



@RestController
@RequestMapping("api/chien")
public class ChienRestController {

    @Autowired
    private ChienService srvChien;

    @PostMapping
    public ChienResponse ajouter(@RequestBody CreateChienRequest request) {
        Chien chien = srvChien.insert(request);
        return ChienResponse.convert(chien);
    }

    @PutMapping("/{id}")
    public ChienResponse modifier(@PathVariable Integer id, @RequestBody UpdateChienRequest request) {
        Chien chien = srvChien.update(id, request);

        return ChienResponse.convert(chien);

}
}
