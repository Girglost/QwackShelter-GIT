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
import org.springframework.web.bind.annotation.RestController;

import qwack_boot.api.requestDTO.lieu.CreateLieuRequest;
import qwack_boot.api.requestDTO.lieu.UpdateLieuRequest;
import qwack_boot.api.responseDTO.lieu.LieuResponse;
import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;
import qwack_boot.service.LieuService;

@RestController
@RequestMapping("api/lieu")
public class LieuRestController {

    @Autowired
    LieuService srvLieu;

    @GetMapping
    public List<LieuResponse> chercherTous() {
        return srvLieu.getAll().stream().map(a -> LieuResponse.convert(a)).toList();
    }

    @GetMapping("/{id}")
    public LieuResponse chercherParId(@PathVariable Integer id) {
        Lieu lieu = srvLieu.getById(id);
        return LieuResponse.convert(lieu);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        srvLieu.delete(id);
    }

    @PostMapping
    public LieuResponse ajouter(@RequestBody CreateLieuRequest emp) {
        Lieu lieu = srvLieu.insert(emp);

        return LieuResponse.convert(lieu);

    }

    @PutMapping("/{id}")
    public void modifier(@PathVariable Integer id, @RequestBody UpdateLieuRequest emp) {
        srvLieu.update(id, emp);
    }

    @GetMapping("/adresse")
    public LieuResponse chercherParAdresse(Adresse adresse) {
        return LieuResponse.convert(srvLieu.getByAdresse(adresse));
    }
}
