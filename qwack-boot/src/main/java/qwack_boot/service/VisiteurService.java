package qwack_boot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.api.requestDTO.personne.CreateVisiteurRequest;
import qwack_boot.api.requestDTO.personne.UpdateVisiteurRequest;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;

@Service
public class VisiteurService {
    private static final Logger log = LoggerFactory.getLogger(VisiteurService.class);

    @Autowired
    IDAOPersonne daoPersonne;

    @Autowired
    PersonneService personneSrv;

    @Autowired
    LieuService lieuSrv;

    @Autowired
    QuackShelterService quackSrv;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Personne> getAllVisiteur() {
        return daoPersonne.findAllVisiteur();
    }

    public Personne getVisiteurById(Integer id) {
        return daoPersonne.findVisiteurById(id).orElse(null);
    }

    public Personne getVisiteurByIdWithVisites(Integer idPersonne) {
        return daoPersonne.findVisiteurByIdwithVisites(idPersonne);
    }

    public Personne getVisiteurByIdWithAdoptions(Integer idPersonne) {
        return daoPersonne.findVisiteurByIdwithAdoptions(idPersonne);
    }

    public Personne insertVisiteur(CreateVisiteurRequest visiteurRequest) {
        // Si le login est deja existant en base, on envoie une erreur
        if (personneSrv.loginExist(visiteurRequest.getLogin())) {
            log.debug("login {} déja utilisé", visiteurRequest.getLogin());
            throw new IllegalArgumentException("Login déjà utilisé");
        }
        String login = visiteurRequest.getLogin();

        String passwordEncoded = passwordEncoder.encode(visiteurRequest.getPassword());
        String nom = visiteurRequest.getNom();
        String prenom = visiteurRequest.getPrenom();

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(visiteurRequest.getHabitation());

        QuackShelter quackShelter = quackSrv.getById(visiteurRequest.getQuackShelterId());
        Personne visiteur = Personne.createVisiteur(nom, prenom, login, passwordEncoded, habitation, quackShelter);

        System.out.println("Password avant encode : " + visiteurRequest.getPassword());
        System.out.println("Password APRES encode : " + passwordEncoded);

        return daoPersonne.save(visiteur);
    }

    @Transactional
    public Personne updateVisiteur(Integer id, UpdateVisiteurRequest visiteurRequest) {

        Personne personne = daoPersonne.findById(id).orElse(null);

        if (personne == null) {
            log.debug("Visiteur {} introuvable", id);
            throw new IllegalArgumentException("Visiteur introuvable");

        }
        // on modifie les champs que VISITEUR DTO a donné

        personne.setNom(visiteurRequest.getNom());
        personne.setPrenom(visiteurRequest.getPrenom());

        Lieu lieu = lieuSrv.findOrCreate(visiteurRequest.getHabitation());

        personne.setHabitation(lieu);

        personne.setPassword(passwordEncoder.encode(personne.getPassword()));

        QuackShelter quackShelter = quackSrv.getById(visiteurRequest.getQuackShelterId());
        personne.setQuackShelter(quackShelter);
        return daoPersonne.save(personne);
    }

    public void delete(Personne personne) {
        daoPersonne.deleteById(personne.getId());
    }

    public void deleteById(Integer id) {

        daoPersonne.deleteById(id);
    }

}
