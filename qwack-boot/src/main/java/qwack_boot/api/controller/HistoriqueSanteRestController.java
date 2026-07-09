package qwack_boot.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.CreateHistoriqueSanteRequest;
import qwack_boot.api.requestDTO.UpdateHistoriqueSanteRequest;
import qwack_boot.api.responseDTO.HistoriqueSanteReponse;
import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;
import qwack_boot.service.AnimalService;
import qwack_boot.service.HistoriqueSanteService;


@RestController
@RequestMapping("/api/hSante")
public class HistoriqueSanteRestController {
    
    final HistoriqueSanteService hsSrv;
    final AnimalService animalSrv;

    HistoriqueSanteRestController(HistoriqueSanteService hsSrv, AnimalService animalSrv) {
        this.hsSrv = hsSrv;
        this.animalSrv = animalSrv;
    }

    @GetMapping
    public List<HistoriqueSanteReponse> chercherTous() {
        return hsSrv.getAll().stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    
    @GetMapping("/{id}")
    public HistoriqueSanteReponse chercherParId(@PathVariable Integer id) {
        HistoriqueSante hs = hsSrv.getById(id);
        return HistoriqueSanteReponse.convert(hs);
    }
    
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id){
        hsSrv.delete(id);
    }

    @PostMapping
    public HistoriqueSanteReponse ajouter(@RequestBody CreateHistoriqueSanteRequest request) {
        HistoriqueSante hs = new HistoriqueSante(
            request.commentaire(),
            request.poids(),
            request.cause(),
            animalSrv.getById(request.animalId())
        );

        hsSrv.insert(hs);
        return HistoriqueSanteReponse.convert(hs);

        
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody UpdateHistoriqueSanteRequest request) {
        HistoriqueSante hs = new HistoriqueSante();

        hs.setCause(request.cause());
        hs.setCommentaire(request.commentaire());
        hs.setAnimal(animalSrv.getById(request.animalId()));
        hs.setDate(request.date());
        hs.setDuree(request.duree());
        hs.setHeure(request.heure());
        hs.setPoids(request.poids());

        
        hsSrv.update(id,hs);
    }


    @GetMapping("/cause/{cause}")
    public List<HistoriqueSanteReponse> getByCause(@PathVariable Cause cause) {
        return hsSrv.getByCause(cause).stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    @GetMapping("/comment")
    public List<HistoriqueSanteReponse> getByCommentaireNotNull() {
        return hsSrv.getByCommentaireNotNull().stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    @GetMapping("/between/{date1}/{date2}")
    public List<HistoriqueSanteReponse> getByDateBetween(@PathVariable LocalDate date1, @PathVariable LocalDate date2) {
        return hsSrv.getByDateBetween(date1, date2).stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    @GetMapping("/after/{date}")
    public List<HistoriqueSanteReponse> getByDateAfter(@PathVariable LocalDate date) {
        return hsSrv.getByDateAfter(date).stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    @GetMapping("/before/{date}")
    public List<HistoriqueSanteReponse> getByDateBefore(@PathVariable LocalDate date) {
        return hsSrv.getByDateBefore(date).stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }
    @GetMapping("/animal/{AnimalId}")
    public List<HistoriqueSanteReponse> getByAnimalId(@PathVariable Integer AnimalId) {
        return hsSrv.getByAnimalId(AnimalId).stream().map(h -> HistoriqueSanteReponse.convert(h)).toList();
    }

    


    
}
