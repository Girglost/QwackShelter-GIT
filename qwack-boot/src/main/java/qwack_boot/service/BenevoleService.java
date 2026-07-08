package qwack_boot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutActivite;
import qwack_boot.model.StatutAnimal;

@Service
public class BenevoleService {

    private static final Logger log = LoggerFactory.getLogger(VisiteurService.class);

    @Autowired
    IDAOPersonne daoPersonne;

    @Autowired
    PersonneService personneSrv;

    @Autowired
    StatutAnimalService statutAnimalSrv;

    @Autowired
    LieuService lieuSrv;
    @Autowired
    VisiteService visiteSrv;
    @Autowired
    AnimalService animalSrv;

    @Autowired
    QuackShelterService quackSrv;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Personne> getAllBenevole() {
        return daoPersonne.findAllBenevole();
    }

    public Personne getBenevoleById(Integer id) {
        return daoPersonne.findBenevoleById(id).orElse(null);
    }

    public Personne getBenevoleByIdWithVisites(Integer idPersonne) {
        return daoPersonne.findBenevoleByIdwithVisites(idPersonne);
    }

    public Personne getBenevoleByIdWithAdoptions(Integer idPersonne) {
        return daoPersonne.findBenevoleByIdwithAdoptions(idPersonne);
    }

    public Personne insertBenevole(Personne benevole) {
        // Si le login est deja existant en base, on envoie une erreur
        if (personneSrv.loginExist(benevole.getLogin())) {
            log.debug("login {} déja utilisé", benevole.getLogin());
            throw new IllegalArgumentException("Login déjà utilisé");
        }
        String login = benevole.getLogin();

        String passwordEncoded = passwordEncoder.encode(benevole.getPassword());
        String nom = benevole.getNom();
        String prenom = benevole.getPrenom();

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(benevole.getHabitation());

        QuackShelter quackShelter = benevole.getQuackShelter();
        Personne newBenevole = Personne.createBenevole(nom, prenom, login, passwordEncoded, habitation,
                quackShelter);

        System.out.println("Password avant encode : " + newBenevole.getPassword());
        System.out.println("Password APRES encode : " + passwordEncoded);

        return daoPersonne.save(newBenevole);
    }

    @Transactional
    public Personne updateBenevole(Integer id, Personne benevole) {

        Personne benevoleUpdate = daoPersonne.findById(id).orElse(null);

        if (benevoleUpdate == null) {
            log.debug("Benevole {} introuvable", id);
            throw new IllegalArgumentException("Benevole introuvable");
        }
        // on modifie les champs que VISITEUR DTO a donné
        benevoleUpdate.setNom(benevole.getNom());
        benevoleUpdate.setPrenom(benevole.getPrenom());
        benevoleUpdate.setStatutActivite(benevole.getStatutActivite());
        benevoleUpdate.setPassword(passwordEncoder.encode(benevoleUpdate.getPassword()));

        Lieu lieu = lieuSrv.findOrCreate(benevole.getHabitation());
        benevoleUpdate.setHabitation(lieu);

        QuackShelter quackShelter = benevole.getQuackShelter();
        benevoleUpdate.setQuackShelter(quackShelter);

        return daoPersonne.save(benevoleUpdate);
    }

    @Transactional
    public BenevoleResponse partirEnBalade(StatutAnimal statutAnimal, Personne benevole) {
        benevole.setStatutActivite(StatutActivite.BALADE);
        statutAnimal.setStatut(Statut.EnBalade);
        Integer id = statutAnimal.getId();
        statutAnimalSrv.update(id, statutAnimal);

        return BenevoleResponse.convert(daoPersonne.save(benevole));
    }
}
