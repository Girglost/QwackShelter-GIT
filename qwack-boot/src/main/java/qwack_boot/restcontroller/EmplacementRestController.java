package qwack_boot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.model.Emplacement;
import qwack_boot.service.EmplacementService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/emplacement")
public class EmplacementRestController {
    
    @Autowired
    EmplacementService EmpSrv;

    @GetMapping
    public List<Emplacement> chercherTous() {
        return EmpSrv.getAll();
    }
    
    @GetMapping("/{id}")
    public Emplacement chercherParId(@RequestParam Integer id) {
        return EmpSrv.getById(id);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        EmpSrv.delete(id);
    }

    @PostMapping
    public void ajouter(@RequestBody Emplacement emp) {
        EmpSrv.insert(emp);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody Emplacement emp) {
        emp.setId(id);
        EmpSrv.update(emp);
    }
    
}
