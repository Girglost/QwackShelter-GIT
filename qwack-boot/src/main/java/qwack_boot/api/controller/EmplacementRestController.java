package qwack_boot.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.CreateOrUpdateEmplacementRequest;
import qwack_boot.api.responseDTO.EmplacementReponse;
import qwack_boot.model.Emplacement;
import qwack_boot.model.typeBox;
import qwack_boot.service.EmplacementService;


@RestController
@RequestMapping("/api/emplacement")
public class EmplacementRestController {
    
    final EmplacementService EmpSrv;

    EmplacementRestController(EmplacementService EmpSrv) {
        this.EmpSrv = EmpSrv;
    }

    @GetMapping
    public List<EmplacementReponse> chercherTous() {
        return EmpSrv.getAll().stream().map(e -> EmplacementReponse.convert(e)).toList();
    }
    
    @GetMapping("/{id}")
    public EmplacementReponse chercherParId(@RequestParam Integer id) {
        Emplacement e = EmpSrv.getById(id);
        return EmplacementReponse.convert(e);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        EmpSrv.delete(id);
    }

    @PostMapping
    public EmplacementReponse ajouter(@RequestBody CreateOrUpdateEmplacementRequest emp) {
        Emplacement e = new Emplacement();

        e.setBox(emp.box());
        e.setComplet(emp.complet());
        e.setNbPlace(emp.nb_place());

        EmpSrv.insert(e);
        return EmplacementReponse.convert(e);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody CreateOrUpdateEmplacementRequest emp) {
        Emplacement e = new Emplacement();

        e.setBox(emp.box());
        e.setComplet(emp.complet());
        e.setNbPlace(emp.nb_place());

        EmpSrv.update(id,e);
    }


    @GetMapping("/complet")
    public List<Emplacement> getByCompletTrue() {
        return EmpSrv.getByCompletTrue();
    }

    @GetMapping("/dispo")
    public List<Emplacement> getByCompletFalse() {
        return EmpSrv.getByCompletFalse();
    }

    @GetMapping("/{box}")
    public List<Emplacement> getByBox(@RequestParam typeBox box) {
        return EmpSrv.getByBox(box);
    }
    
    
    
}
