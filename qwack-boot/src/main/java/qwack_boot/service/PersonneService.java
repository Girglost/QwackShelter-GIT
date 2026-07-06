package qwack_boot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import qwack_boot.dao.IDAOLieu;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.dao.IDAOVisite;
import qwack_boot.dto.personne.VisiteurUpdateDTO;
import qwack_boot.model.Adresse;
import qwack_boot.model.Animal;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Role;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutValidation;
import qwack_boot.model.TypeLieu;
import qwack_boot.model.Visite;

@Service
public class PersonneService {
	private static final Logger log = LoggerFactory.getLogger(PersonneService.class);
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
	@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

	public Personne getPatronById(Integer id) {
		return daoPersonne.findPatronById(id).orElse(null);
	}

	public Personne getBenevoleById(Integer id) {
		return daoPersonne.findBenevoleById(id).orElse(null);
	}

	public Personne getEmployeById(Integer id) {
		return daoPersonne.findEmployeById(id).orElse(null);
	}

	public Personne getVisiteurById(Integer id) {
		return daoPersonne.findVisiteurById(id).orElse(null);
	}

	public Personne getByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findByIdwithVisites(idPersonne);
	}

	public Personne getByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findByIdwithAdoptions(idPersonne);
	}

	public Personne getVisiteurByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findVisiteurByIdwithVisites(idPersonne);
	}

	public Personne getVisiteurByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findVisiteurByIdwithAdoptions(idPersonne);
	}

	public Personne getPatronByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findPatronByIdwithVisites(idPersonne);
	}

	public Personne getPatronByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findPatronByIdwithAdoptions(idPersonne);
	}

	public Personne getEmployeByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findEmployeByIdwithVisites(idPersonne);
	}

	public Personne getEmployeByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findEmployeByIdwithAdoptions(idPersonne);
	}

	public Personne getBenevoleByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findBenevoleByIdwithVisites(idPersonne);
	}

	public Personne getBenevoleByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findBenevoleByIdwithAdoptions(idPersonne);
	}

	public Personne getByLoginAndPassword(String login, String password) {
		return daoPersonne.findByLoginAndPassword(login, password);
	}

	public boolean loginExist(String login) {
		return daoPersonne.existsByLogin(login);
	}

	public Personne insert(Personne personne) {

		TypeLieu typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();

		Lieu lieu = daoLieu.findByAdresse(adresse);
		System.out.println("Lieu trouvé " + lieu);
		if (lieu == null) {

			lieu = new Lieu(typeLieu, adresse.getNumero(), adresse.getVoie(), adresse.getVille(), adresse.getCp());
			lieuSrv.insert(lieu);

			personne.setHabitation(lieu);
		}

		if (loginExist(personne.getLogin())) {
			throw new IllegalArgumentException("Login déjà utilisé");
		}
		System.out.println("PASSWORD AVANT ENCODE = " + personne.getPassword());
		personne.setPassword(passwordEncoder.encode(personne.getPassword()));
		System.out.println("ENCODED PASSWORD = " + personne.getPassword());
		personne = daoPersonne.save(personne);
		return personne;
	}

	@Transactional
	public Personne update(Integer id, VisiteurUpdateDTO visiteurUpdateDTO) {

		Personne personne = daoPersonne.findById(id).orElse(null);

		// on modifie les champs que VISITEUR DTO a donné

		personne.setNom(visiteurUpdateDTO.getNom());
		personne.setNom(visiteurUpdateDTO.getPrenom());

		Lieu lieu = lieuSrv.findOrCreate(visiteurUpdateDTO.getHabitation());

		personne.setHabitation(lieu);

		personne.setPassword(passwordEncoder.encode(personne.getPassword()));

		return daoPersonne.save(personne);
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

		Visite visite = new Visite(personne, animal, quackShelter, dateVisite, StatutValidation.EN_ATTENTE);
		visiteSrv.insert(visite);

		/*
		 * personne = daoPersonne.findBenevoleByIdwithVisites(personne.getId());
		 * List<Visite> visites = personne.getVisites();
		 * visites.add(visite);
		 * personne.setVisites(visites);
		 * 
		 * // Pas besoin de ca, c'est géré coté visite
		 * daoPersonne.save(personne);
		 */
		System.out.println("Votre demande de visite est en attente pour : " + visite.getDateVisite());
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

			personne = daoPersonne.findBenevoleByIdwithAdoptions(personne.getId());

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

	public Personne getByLogin(String login) {
		return daoPersonne.findByLogin(login);
	}

}
