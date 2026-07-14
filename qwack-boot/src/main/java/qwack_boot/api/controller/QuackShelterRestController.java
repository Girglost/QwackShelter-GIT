package qwack_boot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.QuackShelterDTO;
import qwack_boot.model.QuackShelter;
import qwack_boot.service.QuackShelterService;

@RestController
@RequestMapping("api/quackshelter")
public class QuackShelterRestController {

    @Autowired
    QuackShelterService QuackShelterSrv;

    @GetMapping
    public List<QuackShelterDTO> chercherTous() {
        return QuackShelterSrv.getAll().stream().map(q -> QuackShelterDTO.convert(q)).toList();
    }

    @GetMapping("/{id}")
    public QuackShelterDTO chercherParId(@RequestParam Integer id) {
        return QuackShelterDTO.convert(QuackShelterSrv.getById(id));
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        QuackShelterSrv.delete(id);
    }

    @PostMapping
    public void ajouter(@RequestBody QuackShelter emp) {
        QuackShelterSrv.insert(emp);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody QuackShelter emp) {
        emp.setId(id);
        QuackShelterSrv.update(emp);
    }

}
