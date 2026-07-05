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

import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;
import qwack_boot.service.HistoriqueSanteService;


@RestController
@RequestMapping("/api/hSante")
public class HistoriqueSanteRestController {
    
    @Autowired
    HistoriqueSanteService hsSrv;

    @GetMapping
    public List<HistoriqueSante> chercherTous() {
        return hsSrv.getAll();
    }
    
    @GetMapping("/{id}")
    public HistoriqueSante chercherParId(@RequestParam Integer id) {
        return hsSrv.getById(id);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        hsSrv.delete(id);
    }

    @PostMapping
    public void ajouter(@RequestBody HistoriqueSante hs) {
        hsSrv.insert(hs);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody HistoriqueSante hs) {
        hs.setId(id);
        hsSrv.update(hs);
    }


    @GetMapping("/{cause}")
    public List<HistoriqueSante> getByCause(@RequestParam Cause cause) {
        return hsSrv.getByCause(cause);
    }
    @GetMapping("/comment")
    public List<HistoriqueSante> getByCommentaireNotNull() {
        return hsSrv.getByCommentaireNotNull();
    }
    @GetMapping("/{date1}_{date2}")
    public List<HistoriqueSante> getByDateBetween(@RequestParam LocalDate date1, @RequestParam LocalDate date2) {
        return hsSrv.getByDateBetween(date1, date2);
    }
    @GetMapping("/after/{date}")
    public List<HistoriqueSante> getByDateAfter(@RequestParam LocalDate date) {
        return hsSrv.getByDateAfter(date);
    }
    @GetMapping("/before/{date}")
    public List<HistoriqueSante> getByDateBefore(@RequestParam LocalDate date) {
        return hsSrv.getByDateBefore(date);
    }
    @GetMapping("/animal_{AnimalId}")
    public List<HistoriqueSante> getByAnimalId(@RequestParam Integer id) {
        return hsSrv.getByAnimalId(id);
    }

    


    
}
