package qwack_boot.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import qwack_boot.dao.IDAOPersonne;
import qwack_boot.model.Animal;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutAnimal;
import qwack_boot.model.StatutValidation;
import qwack_boot.model.Visite;

public class EmployeService {
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

    public List<Personne> getAllEmploye() {
        return daoPersonne.findAllEmploye();
    }

    public Personne getEmployeById(Integer id) {
        return daoPersonne.findEmployeById(id).orElse(null);
    }

    public Personne insertEmploye(Personne employe) {
        // Si le login est deja existant en base, on envoie une erreur
        if (personneSrv.loginExist(employe.getLogin())) {
            log.debug("login {} déja utilisé", employe.getLogin());
            throw new IllegalArgumentException("Login déjà utilisé");
        }

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(employe.getHabitation());
        employe.setHabitation(employe.getHabitation());

        employe.setPassword(passwordEncoder.encode(employe.getPassword()));
        return daoPersonne.save(employe);
    }

    @Transactional
    public Personne updateVisiteur(Integer id, Personne employe) {

        Personne employeUpdate = daoPersonne.findById(id).orElse(null);

        if (employeUpdate == null) {
            log.debug("Employe {} introuvable", id);
            throw new IllegalArgumentException("Employe introuvable");

        }
        // on modifie les champs que VISITEUR DTO a donné

        employeUpdate.setNom(employe.getNom());
        employeUpdate.setPrenom(employe.getPrenom());

        employeUpdate.setStatutActivite(employe.getStatutActivite());

        Lieu lieu = lieuSrv.findOrCreate(employe.getHabitation());
        employeUpdate.setHabitation(lieu);

        employeUpdate.setPassword(passwordEncoder.encode(employeUpdate.getPassword()));

        QuackShelter quackShelter = employe.getQuackShelter();
        employeUpdate.setQuackShelter(quackShelter);

        return daoPersonne.save(employeUpdate);
    }

    @Transactional
    public StatutAnimal accepterAdoption(int idPersonne, int idAdoption, int idAnimal) {

        Personne personne = personneSrv.getById(idPersonne);
        Animal animalAdopted = animalSrv.getById(idAnimal);
        StatutAnimal statutAdopted = statutAnimalSrv.getById(idAdoption);
        System.out.println("StatutAnimal trouvé :" + statutAdopted);

        statutAdopted.setAdoptant(personne);
        statutAdopted.setAnimal(animalAdopted);
        statutAdopted.setStatut(Statut.Adopte);
        statutAdopted.setDateDepart(LocalDate.now());

        statutAdopted.setStatutAdoption(StatutValidation.EN_ATTENTE);

        System.out.println(animalAdopted.getStatutAnimal());
        System.out.println("Adoption Réussie ! ");

        return statutAnimalSrv.update(statutAdopted.getId(), statutAdopted);
    }

    @Transactional
    public Visite accepterStatut(int idVisite) {
        Visite visite = visiteSrv.getById(idVisite);
        visite.setStatutVisite(StatutValidation.ACCEPTE);
        System.out.println("Visite acceptée ! ");

        return visiteSrv.update(visite);
    }
}
