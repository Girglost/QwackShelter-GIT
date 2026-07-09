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
import qwack_boot.model.Animal;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutActivite;
import qwack_boot.model.StatutAnimal;
import qwack_boot.model.StatutValidation;

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

        // On cherche le Lieu, si il existe ok, sinon on le créé
        Lieu habitation = lieuSrv.findOrCreate(benevole.getHabitation());
        benevole.setHabitation(habitation);
        benevole.setPassword(passwordEncoder.encode(benevole.getPassword()));

        return daoPersonne.save(benevole);
    }

    @Transactional
    public Personne updateBenevole(Integer id, Personne benevole) {

        Personne benevoleUpdate = daoPersonne.findById(id).orElse(null);

        if (benevoleUpdate == null) {
            log.debug("Benevole {} introuvable", id);
            throw new IllegalArgumentException("Benevole introuvable");
        }
        // on modifie les champs que BENEVOLE DTO a donné
        benevoleUpdate.setNom(benevole.getNom());
        benevoleUpdate.setPrenom(benevole.getPrenom());
        benevoleUpdate.setStatutActivite(benevole.getStatutActivite());

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

    @Transactional
    public StatutAnimal demanderAdoption(int idQuackShelter, int idPersonne, int idAnimal) {

        Animal animalAdopted = animalSrv.getById(idAnimal);
        Personne personne = personneSrv.getById(idPersonne);
        System.out.println("Personne trouvé :" + personne);

        StatutAnimal statutAdopted = statutAnimalSrv.getByAnimalId(idAnimal);
        System.out.println("StatutAnimal trouvé :" + statutAdopted);
        // long nbVisite = visiteSrv.NbVisitesByIdAnimalAndIdVisiteur(idAnimal,
        // idPersonne, StatutValidation.ACCEPTE);

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
