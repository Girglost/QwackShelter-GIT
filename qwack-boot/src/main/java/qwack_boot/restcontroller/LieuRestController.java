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

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.service.LieuService;

@RestController
@RequestMapping("/lieu")
public class LieuRestController {
    

     @Autowired
    LieuService LieuSrv;

    @GetMapping
    public List<Lieu> chercherTous() {
        return LieuSrv.getAll();
    }
    
    @GetMapping("/{id}")
    public Lieu chercherParId(@RequestParam Integer id) {
        return LieuSrv.getById(id);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        LieuSrv.delete(id);
    }

    @PostMapping
    public void ajouter(@RequestBody Lieu emp) {
        LieuSrv.insert(emp);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody Lieu emp) {
        emp.setId(id);
        LieuSrv.update(emp);
    }

    @GetMapping("/adresse")
    public Lieu chercherParAdresse(Adresse adresse) {
        return LieuSrv.getByAdresse(adresse);
    }
}
