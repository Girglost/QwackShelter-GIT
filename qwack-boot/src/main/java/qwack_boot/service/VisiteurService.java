package qwack_boot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.model.Animal;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Role;
import qwack_boot.model.StatutAnimal;
import qwack_boot.model.StatutValidation;
import qwack_boot.model.Visite;

@Service
public class VisiteurService {
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

    ///////////////////////// CRUD /////////////////////////////////////

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

    public Personne insertVisiteur(Personne visiteur) {
        // Si le login est deja existant en base, on envoie une erreur
        if (personneSrv.loginExist(visiteur.getLogin())) {
            log.debug("login {} déja utilisé", visiteur.getLogin());
            throw new IllegalArgumentException("Login déjà utilisé");
        }
        String login = visiteur.getLogin();

        String passwordEncoded = passwordEncoder.encode(visiteur.getPassword());
        String nom = visiteur.getNom();
        String prenom = visiteur.getPrenom();

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(visiteur.getHabitation());
        System.out.println(visiteur.getHabitation());
        QuackShelter quackShelter = visiteur.getQuackShelter();
        Personne newVisiteur = Personne.createVisiteur(nom, prenom, login, passwordEncoded, habitation, quackShelter);

        System.out.println("Password avant encode : " + visiteur.getPassword());
        System.out.println("Password APRES encode : " + passwordEncoded);

        return daoPersonne.save(newVisiteur);
    }

    @Transactional
    public Personne updateVisiteur(Integer id, Personne visiteur) {

        Personne visiteurUpdate = daoPersonne.findById(id).orElse(null);

        if (visiteurUpdate == null) {
            log.debug("Visiteur {} introuvable", id);
            throw new IllegalArgumentException("Visiteur introuvable");

        }
        // on modifie les champs que VISITEUR DTO a donné

        visiteurUpdate.setNom(visiteur.getNom());
        visiteurUpdate.setPrenom(visiteur.getPrenom());
        Lieu lieu = lieuSrv.findOrCreate(visiteur.getHabitation());

        visiteurUpdate.setHabitation(lieu);

        visiteurUpdate.setPassword(passwordEncoder.encode(visiteurUpdate.getPassword()));

        QuackShelter quackShelter = visiteur.getQuackShelter();
        visiteurUpdate.setQuackShelter(quackShelter);
        return daoPersonne.save(visiteurUpdate);
    }

    ///////////////////////////////////////////////////////////////////////////// *
    ///////////////////////////////////////////////////////////////////////////// */

    @Transactional
    public Personne transformerEnBenevole(Personne personne) {
        personne.setRole(Role.BENEVOLE);
        personne.setAdmin(false);
        personne.setDateEngagement(LocalDate.now());
        return daoPersonne.save(personne);
    }

    // METHODES INTERACTIONS AVEC SHELTER ( VISITES ADOPTIONS DONS ETC )
    public Visite demanderVisite(int visiteurId, int quackShelterId, LocalDateTime dateVisite, int animalId)

    {
        QuackShelter quackShelter = quackSrv.getById(quackShelterId);
        Animal animal = animalSrv.getById(animalId);
        Personne visiteur = daoPersonne.findById(visiteurId).orElse(null);

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy
        // HH:mm");

        // LocalDateTime dateVisite = LocalDateTime.parse(date, formatter);

        if (visiteur == null) {
            System.out.println("VISITEUR INTROUVABLE");
        } else {

        }
        Visite visite = new Visite(visiteur, animal, quackShelter, dateVisite, StatutValidation.EN_ATTENTE);
        System.out.println("La demande de visite  pour " + visiteur.getLogin() + ", le " + visite.getDateVisite()
                + " est en attente ");

        return visiteSrv.insert(visite);
    }

    @Transactional
    public StatutAnimal demanderAdoption(int idQuackShelter, int idPersonne, int idAnimal) {

        Animal animalAdopted = animalSrv.getById(idAnimal);
        Personne personne = personneSrv.getById(idPersonne);
        System.out.println("Personne trouvé :" + personne);

        StatutAnimal statutAdopted = statutAnimalSrv.getByAnimalId(idAnimal);
        System.out.println("StatutAnimal trouvé :" + statutAdopted);
        long nbVisite = visiteSrv.NbVisitesByIdAnimalAndIdVisiteur(idAnimal, idPersonne, StatutValidation.ACCEPTE);

        /* if (nbVisite >= 1) { */

        /*
         * statutAdopted.setAdoptant(personne);
         * statutAdopted.setAnimal(animalAdopted);
         * statutAdopted.setStatut(Statut.Adopte);
         * statutAdopted.setDateDepart(LocalDate.now());
         */

        statutAdopted.setStatutAdoption(StatutValidation.EN_ATTENTE);

        System.out.println(animalAdopted.getStatutAnimal());
        System.out.println("Adoption demandé, en attente de validation ! ");
        // System.out.println(animalAdopted + " a bien été adopté !");
        /*
         * } else {
         * System.out.println("Pour adopter un animal, vous devez lui rendre visite");
         * }
         */
        return statutAnimalSrv.update(statutAdopted.getId(), statutAdopted);
    }

}
