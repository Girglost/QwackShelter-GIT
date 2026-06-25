package qwack_boot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.dao.IDAOLieu;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.dao.IDAOVisite;
import qwack_boot.model.Adresse;
import qwack_boot.model.Animal;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Role;
import qwack_boot.model.Statut;
import qwack_boot.model.TypeLieu;
import qwack_boot.model.Visite;

@Service
public class PersonneService {
	@Autowired
	IDAOPersonne daoPersonne;
	@Autowired
	IDAOLieu daoLieu;
	@Autowired
	LieuService lieuSrv;
	@Autowired
	QuackShelterService quackSrv;
	@Autowired
	VisiteService visiteSrv;
	@Autowired
	AnimalService animalSrv;
	@Autowired
	IDAOVisite daoVisite;
	@Autowired
	StatutAnimalService statutAnimalSrv;

	public List<Personne> getAll() {
		return daoPersonne.findAll();
	}

	public List<Personne> getAllBenevole() {
		return daoPersonne.findAllBenevole();
	}

	public List<Personne> getAllEmploye() {
		return daoPersonne.findAllEmploye();
	}

	public List<Personne> getAllVisiteur() {
		return daoPersonne.findAllVisiteur();
	}

	public List<Personne> getAllPatron() {
		return daoPersonne.findAllPatron();
	}

	public Personne getById(Integer id) {
		return daoPersonne.findById(id).orElse(null);
	}

	public Personne getByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findbyIdwithVisites(idPersonne);
	}

	public Personne getByLoginAndPassword(String login, String password) {
		return daoPersonne.findByLoginAndPassword(login, password);
	}

	public boolean loginExist(String login) {
		return daoPersonne.existsByLogin(login);
	}

	public void insert(Personne personne) {
		// Permet d'insert un Lieu en cascade si il n'est pas en bdd au moment de la
		// creation de la personne
		Lieu lieuPersonne = personne.getHabitation();
		// System.out.println("Adresse de la personne "+lieuPersonne);
		TypeLieu typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();

		Lieu lieu = daoLieu.findByAdresse(adresse);
		// System.out.println("Lieu trouvé "+lieu);
		if (lieu == null) {

			lieu = new Lieu(typeLieu, adresse.getNumero(), adresse.getVoie(), adresse.getVille(), adresse.getCp());
			daoLieu.save(lieu);
			// System.out.println(lieu);
			personne.setHabitation(lieu);
		}
		// System.out.println(personne);

		if (loginExist(personne.getLogin())) {
			throw new IllegalArgumentException("Login déjà utilisé");
		}

		personne = daoPersonne.save(personne);
	}

	public void update(Personne personne) {
		// Permet d'insert un Lieu en cascade si il n'est pas en bdd au moment de la
		// creation de la personne
		Lieu lieuPersonne = personne.getHabitation();
		// System.out.println("Adresse de la personne "+lieuPersonne);
		TypeLieu typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();

		Lieu lieu = daoLieu.findByAdresse(adresse);
		// System.out.println("Lieu trouvé "+lieu);
		if (lieu == null) {

			lieu = new Lieu(typeLieu, adresse.getNumero(), adresse.getVoie(), adresse.getVille(), adresse.getCp());
			daoLieu.save(lieu);
			// System.out.println(lieu);
			personne.setHabitation(lieu);
		}

		personne = daoPersonne.save(personne);
	}

	public void delete(Personne personne) {
		daoPersonne.deleteById(personne.getId());
	}

	public void deleteById(Integer id) {

		daoPersonne.deleteById(id);
	}

	// METHODES INTERACTIONS AVEC SHELTER ( VISITES ADOPTIONS DONS ETC )
	public void demanderVisite(Personne personne, int idQuackShelter, String date, int idAnimal) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);
		Animal animal = animalSrv.getById(idAnimal);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		LocalDateTime dateVisite = LocalDateTime.parse(date, formatter);

		Visite visite = new Visite(personne, animal, quackShelter, dateVisite);
		visiteSrv.insert(visite);

		personne = daoPersonne.findbyIdwithVisites(personne.getId());

		personne.getVisites().add(visite);

		daoPersonne.save(personne);

		System.out.println("Votre visite est reservé le " + visite.getDateVisite());
	}

	public void faireDon(int idQuackShelter, double don) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);
		quackShelter.setTresorerie(quackShelter.getTresorerie() + don);
		quackSrv.update(quackShelter);
		System.out.println("La trésorerie du quackShelter s'élève maintenant a " + quackShelter.getTresorerie() + " €");

	}

	public void demanderAdoption(int idQuackShelter, Personne personne, int idAnimal) {

		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);

		Animal animalAdopted = animalSrv.getById(idAnimal);
		System.out.println("personne id " + personne.getId());
		long nbVisite = visiteSrv.NbVisitesByIdAnimalAndIdVisiteur(idAnimal, personne.getId());

		if (nbVisite >= 1) {

			animalAdopted.getStatutAnimal().setAdoptant(personne);
			animalAdopted.getStatutAnimal().setAnimal(animalAdopted);
			animalAdopted.getStatutAnimal().setStatut(Statut.Adopte);
			animalAdopted.getStatutAnimal().setDateDepart(LocalDate.now());

			System.out.println("Adoption réussi ! ");
			System.out.println(animalAdopted.getStatutAnimal());

			animalSrv.update(animalAdopted);

			personne = daoPersonne.findbyIdwithAdoptions(personne.getId());

			personne.getAdoptions().add(animalAdopted.getStatutAnimal());
			daoPersonne.save(personne);
			System.out.println(animalAdopted + " a bien été adopté !");
		} else {
			System.out.println("Pour adopter un animal, vous devez lui rendre visite");
		}
	}

	@Transactional
	public Personne transformerEnBenevole(Personne personne) {
		personne.setRole(Role.BENEVOLE);
		personne.setAdmin(false);
		personne.setDateEngagement(LocalDate.now());
		return daoPersonne.save(personne);
	}

	public List<Personne> getByRoleIn(List<Role> roles) {

		return daoPersonne.findByRoleIn(roles);
	}
}
