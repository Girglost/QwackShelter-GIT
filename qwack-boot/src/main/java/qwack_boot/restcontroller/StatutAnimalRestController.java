package qwack_boot.restcontroller;

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

import qwack_boot.model.StatutAnimal;
import qwack_boot.service.StatutAnimalService;





@RestController
@RequestMapping("/api/hSante")
public class StatutAnimalRestController {
    
    @Autowired
    StatutAnimalService saSrv;

    @GetMapping
    public List<StatutAnimal> chercherTous() {
        return saSrv.getAll();
    }
    
    @GetMapping("/{id}")
    public StatutAnimal chercherParId(@RequestParam Integer id) {
        return saSrv.getById(id);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        saSrv.delete(id);
    }

    @PostMapping
    public void ajouter(@RequestBody StatutAnimal hs) {
        saSrv.insert(hs);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody StatutAnimal hs) {
        hs.setId(id);
        saSrv.update(hs);
    }
    
}
