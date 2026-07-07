package qwack_boot.api.controller.animaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.animal.CreateNACRequest;
import qwack_boot.api.requestDTO.animal.UpdateNACRequest;

import qwack_boot.api.responseDTO.animal.NACResponse;

import qwack_boot.model.NAC;

import qwack_boot.service.NACService;

@RestController
@RequestMapping("api/nac")
public class NACRestController {

    @Autowired
    private NACService srvNAC;

    @PostMapping
    public NACResponse ajouter(@RequestBody CreateNACRequest request) {
        NAC nac = srvNAC.insert(request);
        return NACResponse.convert(nac);
    }

    @PutMapping("/{id}")
    public NACResponse modifier(@PathVariable Integer id, @RequestBody UpdateNACRequest request) {
        NAC nac = srvNAC.update(id, request);

        return NACResponse.convert(nac);

    }

}
