package qwack_boot.restcontroller;

import java.time.LocalDate;
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

import qwack_boot.model.Animal;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;
import qwack_boot.service.StatutAnimalService;

@RestController
@RequestMapping("/api/statutAnimal")
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
    public void supprimer(@PathVariable Integer id) {
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

    @GetMapping("/dispo")
    public List<Animal> dispo() {
        return saSrv.getByDispo();
    }
    
    @GetMapping("/{idAdoptant}")
    public List<StatutAnimal> getByAdoptant(@RequestParam Integer idAdoptant) {
        return saSrv.getByAdoptantId(idAdoptant);
    }

    @GetMapping("/{idAnimal}")
    public StatutAnimal getByAnimal(@RequestParam Integer id) {
        return saSrv.getByAnimalId(id);
    }
    
    @GetMapping("/{statut}")
    public List<StatutAnimal> getByStatut(Statut statut) {
        return saSrv.getByStatut(statut);
    }

    @GetMapping("/arrivee/{date1}_{date2}")
    public List<StatutAnimal> getByArriveeBetween(@RequestParam LocalDate date1,@RequestParam LocalDate date2) {
        return saSrv.getByDateArriveeBetween(date1, date2);
    }

    @GetMapping("/arrivee/before_{date}")
    public List<StatutAnimal> getByArriveeBefore(@RequestParam LocalDate date) {
        return saSrv.getByDateArriveeBefore(date);
    }
    
    @GetMapping("/arrivee/after_{date}")
    public List<StatutAnimal> getByArriveeAfter(@RequestParam LocalDate date) {
        return saSrv.getByDateArriveeAfter(date);
    }

    @GetMapping("/depart/{date1}_{date2}")
    public List<StatutAnimal> getByDepartBetween(@RequestParam LocalDate date1,@RequestParam LocalDate date2) {
        return saSrv.getByDateDepartBetween(date1, date2);
    }

    @GetMapping("/depart/before_{date}")
    public List<StatutAnimal> getByDepartBefore(@RequestParam LocalDate date) {
        return saSrv.getByDateDepartBefore(date);
    }
    
    @GetMapping("/depart/after_{date}")
    public List<StatutAnimal> getByDepartAfter(@RequestParam LocalDate date) {
        return saSrv.getByDateDepartAfter(date);
    }
}
