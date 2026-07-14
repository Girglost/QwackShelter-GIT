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
import qwack_boot.api.responseDTO.personne.BenevoleResponse;
import qwack_boot.dao.IDAOLieu;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.dao.IDAOVisite;
import qwack_boot.model.Animal;
import qwack_boot.model.HistoriqueSante;
import qwack_boot.model.Lieu;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Role;
import qwack_boot.model.Statut;
import qwack_boot.model.StatutActivite;
import qwack_boot.model.StatutAnimal;
import qwack_boot.model.StatutValidation;
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
	HistoriqueSanteService historiqueSanteSrv;
	@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// METHODES Générales //////////////////////

	public List<Personne> getAll() {
		return daoPersonne.findAll();
	}

	public Personne getById(Integer id) {
		return daoPersonne.findById(id).orElse(null);
	}

	public Personne getByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findByIdwithVisites(idPersonne);
	}

	public Personne getByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findByIdwithAdoptions(idPersonne);
	}

	public Personne getByLoginAndPassword(String login, String password) {
		return daoPersonne.findByLoginAndPassword(login, password);
	}

	public List<Personne> getByRoleIn(List<Role> roles) {

		return daoPersonne.findByRoleIn(roles);
	}

	public Personne getByLogin(String login) {
		return daoPersonne.findByLogin(login);
	}

	public Personne getProfilByLogin(String login) {
		Personne personne = daoPersonne.findByLogin(login);

		daoPersonne.findByIdwithVisites(personne.getId());
		daoPersonne.findByIdwithAdoptions(personne.getId());

		return personne;
	}

	public boolean loginExist(String login) {
		return daoPersonne.existsByLogin(login);
	}

	public Personne insert(Personne personne) {
		// Si le login est deja existant en base, on envoie une erreur
		if (daoPersonne.existsByLogin(personne.getLogin())) {
			log.debug("login {} déja utilisé", personne.getLogin());
			throw new IllegalArgumentException("Login déjà utilisé");
		}

		// On cherche le Lieu, si il existe ok, sinon on le créé
		Lieu habitation = lieuSrv.findOrCreate(personne.getHabitation());
		personne.setHabitation(habitation);

		personne.setPassword(passwordEncoder.encode(personne.getPassword()));
		return daoPersonne.save(personne);
	}

	@Transactional
	public Personne update(Integer id, Personne personne) {

		Personne personneUpdate = this.getById(id);

		if (personneUpdate == null) {
			log.debug("Visiteur {} introuvable", id);
			throw new IllegalArgumentException("Visiteur introuvable");

		}
		// on modifie les champs que le DTO a donné

		personneUpdate.setNom(personne.getNom());
		personneUpdate.setPrenom(personne.getPrenom());

		Lieu lieu = lieuSrv.findOrCreate(personne.getHabitation());
		personneUpdate.setHabitation(lieu);

		QuackShelter quackShelter = personne.getQuackShelter();
		personneUpdate.setQuackShelter(quackShelter);

		return daoPersonne.save(personneUpdate);
	}

	public void delete(Personne personne) {
		daoPersonne.deleteById(personne.getId());
	}

	public void deleteById(Integer id) {

		daoPersonne.deleteById(id);
	}

	public Personne changePassword(Personne personne) {
		return daoPersonne.save(personne);
	}

	/////////////////////////////////////////////
	// VISITEUR //////////////////////////////////
	/////////////////////////////////////////////
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

	@Transactional
	public Personne transformerEnBenevole(Personne personne) {
		personne.setRole(Role.BENEVOLE);
		personne.setAdmin(false);
		personne.setDateEngagement(LocalDate.now());
		return daoPersonne.save(personne);
	}

	public Visite demanderVisite(int visiteurId, int quackShelterId, LocalDateTime dateVisite, int animalId)

	{
		QuackShelter quackShelter = quackSrv.getById(quackShelterId);
		Animal animal = animalSrv.getById(animalId);
		Personne visiteur = this.getById(visiteurId);

		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy
		// HH:mm");

		// LocalDateTime dateVisite = LocalDateTime.parse(date, formatter);

		if (visiteur == null) {
			System.out.println("VISITEUR INTROUVABLE");
		}
		Visite visite = new Visite(visiteur, animal, quackShelter, dateVisite, StatutValidation.EN_ATTENTE);
		System.out.println("La demande de visite  pour " + visiteur.getLogin() + ", le " + visite.getDateVisite()
				+ " est en attente ");

		return visiteSrv.insert(visite);
	}

	@Transactional
	public StatutAnimal demanderAdoption(int idQuackShelter, int idPersonne, int idAnimal) {

		Animal animalAdopted = animalSrv.getById(idAnimal);
		Personne personne = this.getById(idPersonne);
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

		statutAdopted.setAdoptant(personne);
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

	/////////////////////////////////////////////
	// BENEVOLE //////////////////////////////////
	/////////////////////////////////////////////

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

	@Transactional
	public BenevoleResponse partirEnBalade(StatutAnimal statutAnimal, Personne personne) {
		personne.setStatutActivite(StatutActivite.BALADE);
		statutAnimal.setStatut(Statut.EnBalade);
		Integer id = statutAnimal.getId();
		statutAnimalSrv.update(id, statutAnimal);

		return BenevoleResponse.convert(daoPersonne.save(personne));
	}

	/////////////////////////////////////////////
	// EMPLOYE //////////////////////////////////
	/////////////////////////////////////////////
	public List<Personne> getAllEmploye() {
		return daoPersonne.findAllEmploye();
	}

	public Personne getEmployeById(Integer id) {
		return daoPersonne.findEmployeById(id).orElse(null);
	}

	@Transactional
	public StatutAnimal accepterAdoption(int idAdoption) {
		StatutAnimal statutAdopted = statutAnimalSrv.getById(idAdoption);
		System.out.println("StatutAnimal trouvé :" + statutAdopted);
		if (statutAdopted == null) {
			return statutAdopted;
		}
		Personne personne = statutAdopted.getAdoptant();
		Animal animalAdopted = statutAdopted.getAnimal();
		if (personne == null || animalAdopted == null) {
			System.out.println("Aucune adoption  demandé pour cet animal");
			return statutAdopted;
		}

		statutAdopted.setAdoptant(personne);
		statutAdopted.setAnimal(animalAdopted);
		statutAdopted.setStatut(Statut.Adopte);
		statutAdopted.setDateDepart(LocalDate.now());

		statutAdopted.setStatutAdoption(StatutValidation.ACCEPTE);

		return statutAnimalSrv.update(statutAdopted.getId(), statutAdopted);
	}

	@Transactional
	public Visite accepterVisite(int idVisite) {
		Visite visite = visiteSrv.getById(idVisite);
		if (visite == null) {
			return visite;
		}
		Personne visiteur = visite.getVisiteur();
		visiteur.setStatutActivite(StatutActivite.VISITE);
		this.update(visiteur.getId(), visiteur);
		visite.setStatutVisite(StatutValidation.ACCEPTE);
		System.out.println("Visite acceptée ! ");

		return visiteSrv.update(visite);
	}

	public HistoriqueSante partirEnSoin(int idEmploye, HistoriqueSante soin) {
		Personne soigneur = this.getById(idEmploye);
		soigneur.setStatutActivite(StatutActivite.SOIN);
		return historiqueSanteSrv.insert(soin);
	}

	public Animal accueilAnimal(Animal animal) {
		return animalSrv.insert(animal);
	}

	/////////////////////////////////////////////////
	/// PATRON /////////////////////////////////////
	///////////////////////////////////////////////
	public List<Personne> getAllPatron() {
		return daoPersonne.findAllPatron();
	}

	public Personne getPatronById(Integer id) {
		return daoPersonne.findPatronById(id).orElse(null);
	}

	public void faireDon(int idQuackShelter, double don) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);
		quackShelter.setTresorerie(quackShelter.getTresorerie() + don);
		quackSrv.update(quackShelter);
		System.out.println("La trésorerie du quackShelter s'élève maintenant a " + quackShelter.getTresorerie() + " €");

	}

	public List<Personne> getByQuackShelterId(Integer id) {
		return daoPersonne.findByQuackShelterId(id);
	}

	public List<Personne> getByStatutActivite(StatutActivite statut) {
		return daoPersonne.findByStatutActivite(statut);
	}

	public List<Personne> getByAdmin(Boolean admin) {
		return daoPersonne.findByAdmin(admin);
	}

}
