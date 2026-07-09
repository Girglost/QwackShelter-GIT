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

import qwack_boot.api.requestDTO.statutAnimal.CreateStatutAnimalRequest;
import qwack_boot.api.requestDTO.statutAnimal.UpdateStatutAnimalRequest;
import qwack_boot.api.responseDTO.StatutAnimalReponse;
import qwack_boot.api.responseDTO.animal.AnimalResponse;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;
import qwack_boot.service.AnimalService;
import qwack_boot.service.EmplacementService;
import qwack_boot.service.PersonneService;
import qwack_boot.service.StatutAnimalService;

@RestController
@RequestMapping("/api/statutAnimal")
public class StatutAnimalRestController {

    final StatutAnimalService saSrv;
    final AnimalService animalSrv;
    final EmplacementService empsSrv;
    final PersonneService pSrv;

    StatutAnimalRestController(StatutAnimalService saSrv, AnimalService animalSrv, EmplacementService empsSrv, PersonneService pSrv) {
        this.saSrv = saSrv;
        this.animalSrv = animalSrv;
        this.empsSrv = empsSrv;
        this.pSrv = pSrv;
    }

    @GetMapping
    public List<StatutAnimalReponse> chercherTous() {
        return saSrv.getAll().stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/{id}")
    public StatutAnimalReponse chercherParId(@PathVariable Integer id) {
        StatutAnimal sa = saSrv.getById(id);
        return StatutAnimalReponse.convert(sa);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        saSrv.delete(id);
    }

    @PostMapping
    public StatutAnimalReponse ajouter(@RequestBody CreateStatutAnimalRequest sar) {
        
        StatutAnimal sa = new StatutAnimal(
            empsSrv.getById(sar.emplacementId()),
            animalSrv.getById(sar.animalId()));

        saSrv.insert(sa);
        return StatutAnimalReponse.convert(sa);
    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody UpdateStatutAnimalRequest sar) {
       StatutAnimal sa = new StatutAnimal();

        sa.setAnimal(animalSrv.getById(sar.animalId()));
        sa.setDateArrivee(sar.dateArrivee());
        sa.setDateDepart(sar.dateDepart());
        sa.setEmplacement(empsSrv.getById(sar.emplacementId()));
        sa.setStatutAdoption(sar.statutAdoption());
        sa.setAdoptant(
				sar.adoptantId() != null
						? pSrv.getById(sar.adoptantId())
						: null);
        

        saSrv.update(id,sa);
    }




    @GetMapping("/dispo")
    public List<AnimalResponse> dispo() {
        return saSrv.getByDispo().stream().map(a -> AnimalResponse.convert(a)).toList();
    }
    
    @GetMapping("/adoptant/{idAdoptant}")
    public List<StatutAnimalReponse> getByAdoptant(@PathVariable Integer idAdoptant) {
        return saSrv.getByAdoptantId(idAdoptant).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/animal/{idAnimal}")
    public StatutAnimalReponse getByAnimal(@PathVariable Integer idAnimal) {
        return StatutAnimalReponse.convert(saSrv.getByAnimalId(idAnimal));
    }
    
    @GetMapping("/statut/{statut}")
    public List<StatutAnimalReponse> getByStatut(@PathVariable Statut statut) {
        return saSrv.getByStatut(statut).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/arrivee/{date1}/{date2}")
    public List<StatutAnimalReponse> getByArriveeBetween(@PathVariable LocalDate date1,@PathVariable LocalDate date2) {
        return saSrv.getByDateArriveeBetween(date1, date2).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/arrivee/before/{date}")
    public List<StatutAnimalReponse> getByArriveeBefore(@PathVariable LocalDate date) {
        return saSrv.getByDateArriveeBefore(date).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }
    
    @GetMapping("/arrivee/after/{date}")
    public List<StatutAnimalReponse> getByArriveeAfter(@PathVariable LocalDate date) {
        return saSrv.getByDateArriveeAfter(date).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/depart/{date1}/{date2}")
    public List<StatutAnimalReponse> getByDepartBetween(@PathVariable LocalDate date1,@PathVariable LocalDate date2) {
        return saSrv.getByDateDepartBetween(date1, date2).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }

    @GetMapping("/depart/before/{date}")
    public List<StatutAnimalReponse> getByDepartBefore(@PathVariable LocalDate date) {
        return saSrv.getByDateDepartBefore(date).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }
    
    @GetMapping("/depart/after/{date}")
    public List<StatutAnimalReponse> getByDepartAfter(@PathVariable LocalDate date) {
        return saSrv.getByDateDepartAfter(date).stream().map(a -> StatutAnimalReponse.convert(a)).toList();
    }
}
