package quack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import quack.dao.IDAOLieu;
import quack.dao.IDAOPersonne;
import quack.model.Adresse;
import quack.model.Animal;
import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Lieu;
import quack.model.Patron;
import quack.model.Personne;
import quack.model.Personnel;
import quack.model.QuackShelter;
import quack.model.Statut;
import quack.model.Visite;
import quack.model.Visiteur;
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


	public List<Personne> getAll()
	{
		return daoPersonne.findAll();
	}

	public List<Benevole> getAllBenevole()
	{
		return daoPersonne.findAllBenevole();
	}

	public List<Employe> getAllEmploye()
	{
		return daoPersonne.findAllEmploye();
	}

	public List<Visiteur> getAllVisiteur()
	{
		return daoPersonne.findAllVisiteur();
	}

	public List<Patron> getAllPatron()
	{
		return daoPersonne.findAllPatron();
	}

	public List<Personnel> getAllPersonnel()
	{
		return daoPersonne.findAllPersonnel();
	}
	public Personne getByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findbyIdwithVisites(idPersonne);
	}

	public Personne getById(Integer id) 
	{
		return daoPersonne.findById(id).orElse(null);
	}

	public Personne getByLoginAndPassword(String login,String password) 
	{
		return daoPersonne.findByLoginAndPassword(login, password);
	}

	public boolean loginExist(String login) {
		return daoPersonne.existsByLogin(login);
	}

	public void insert(Personne personne) {
		//Permet d'insert un Lieu en cascade si il n'est pas  en bdd au moment de la creation de la personne
		Lieu lieuPersonne = personne.getHabitation();
		//System.out.println("Adresse de la personne "+lieuPersonne);
		String typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();

		Lieu lieu = daoLieu.findByAdresse(adresse);
		//System.out.println("Lieu trouvé "+lieu);
		if(lieu ==null) {

			lieu = new Lieu(typeLieu,adresse.getNumero(),adresse.getVoie(),adresse.getVille(),adresse.getCp());
			daoLieu.save(lieu);
			//System.out.println(lieu);
			personne.setHabitation(lieu);
		}
		//System.out.println(personne);

		if (loginExist(personne.getLogin())) {
			throw new IllegalArgumentException("Login déjà utilisé");
		}

		personne = daoPersonne.save(personne);
	}


	public void update(Personne personne) 
	{
		//Permet d'insert un Lieu en cascade si il n'est pas  en bdd au moment de la creation de la personne
		Lieu lieuPersonne = personne.getHabitation();
		//System.out.println("Adresse de la personne "+lieuPersonne);
		String typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();

		Lieu lieu = daoLieu.findByAdresse(adresse);
		//System.out.println("Lieu trouvé "+lieu);
		if(lieu ==null) {

			lieu = new Lieu(typeLieu,adresse.getNumero(),adresse.getVoie(),adresse.getVille(),adresse.getCp());
			daoLieu.save(lieu);
			//System.out.println(lieu);
			personne.setHabitation(lieu);
		}

		personne = daoPersonne.save(personne);
	}

	public void delete(Personne personne) 
	{
		daoPersonne.deleteById(personne.getId());
	}

	public void deleteById(Integer id) 
	{
		daoPersonne.deleteById(id);
	}


	// METHODES INTERACTIONS AVEC SHELTER ( VISITES ADOPTIONS DONS ETC ) 
	public void demanderVisite(Personne personne,int idQuackShelter, String date) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);

		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		LocalDateTime dateVisite = LocalDateTime.parse(date,formatter);

		Visite visite = new Visite(personne, quackShelter, dateVisite);
		visiteSrv.insert(visite);

		personne = daoPersonne.findbyIdwithVisites(personne.getId());

		personne.getVisites().add(visite);

		daoPersonne.save(personne);

		System.out.println("Votre visite est reservé le " + visite.getDateVisite());
	}

	public void faireDon(int idQuackShelter,double don) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);
		quackShelter.setTresorerie(quackShelter.getTresorerie()+ don);
		quackSrv.update(quackShelter);
		System.out.println("La trésorerie du quackShelter s'élève maintenant a "+quackShelter.getTresorerie()+" €");

	}

	public void demanderAdoption(int idQuackShelter,Personne personne, int idAnimal){


		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);

		Animal animalAdopted = animalSrv.getById(idAnimal);

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
		
	}
}
