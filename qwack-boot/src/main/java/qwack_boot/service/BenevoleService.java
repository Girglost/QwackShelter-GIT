package qwack_boot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.api.requestDTO.personne.CreateBenevoleRequest;
import qwack_boot.api.requestDTO.personne.UpdateBenevoleRequest;
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

    public Personne insertBenevole(CreateBenevoleRequest benevoleRequest) {
        // Si le login est deja existant en base, on envoie une erreur
        if (personneSrv.loginExist(benevoleRequest.getLogin())) {
            log.debug("login {} déja utilisé", benevoleRequest.getLogin());
            throw new IllegalArgumentException("Login déjà utilisé");
        }
        String login = benevoleRequest.getLogin();

        String passwordEncoded = passwordEncoder.encode(benevoleRequest.getPassword());
        String nom = benevoleRequest.getNom();
        String prenom = benevoleRequest.getPrenom();

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(benevoleRequest.getHabitation());

        QuackShelter quackShelter = quackSrv.getById(benevoleRequest.getQuackShelterId());
        Personne benevole = Personne.createBenevole(nom, prenom, login, passwordEncoded, habitation, quackShelter);

        System.out.println("Password avant encode : " + benevoleRequest.getPassword());
        System.out.println("Password APRES encode : " + passwordEncoded);

        return daoPersonne.save(benevole);
    }

    @Transactional
    public Personne updateBenevole(Integer id, UpdateBenevoleRequest benevoleRequest) {

        Personne personne = daoPersonne.findById(id).orElse(null);

        if (personne == null) {
            log.debug("Benevole {} introuvable", id);
            throw new IllegalArgumentException("Benevole introuvable");
        }
        // on modifie les champs que VISITEUR DTO a donné
        personne.setNom(benevoleRequest.getNom());
        personne.setPrenom(benevoleRequest.getPrenom());
        personne.setStatutActivite(benevoleRequest.getStatutActivite());
        personne.setPassword(passwordEncoder.encode(personne.getPassword()));

        Lieu lieu = lieuSrv.findOrCreate(benevoleRequest.getHabitation());
        personne.setHabitation(lieu);

        QuackShelter quackShelter = quackSrv.getById(benevoleRequest.getQuackShelterId());
        personne.setQuackShelter(quackShelter);

        return daoPersonne.save(personne);
    }

    @Transactional
    public BenevoleResponse partirEnBalade(StatutAnimal statutAnimal, Personne benevole) {
        benevole.setStatutActivite(StatutActivite.BALADE);
        statutAnimal.setStatut(Statut.EnBalade);

        statutAnimalSrv.update(statutAnimal);

        return BenevoleResponse.convert(daoPersonne.save(benevole));
    }
}
