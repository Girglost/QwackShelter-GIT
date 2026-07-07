package qwack_boot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import qwack_boot.dao.IDAOLieu;
import qwack_boot.dao.IDAOPersonne;
import qwack_boot.dao.IDAOVisite;
import qwack_boot.model.Personne;
import qwack_boot.model.QuackShelter;
import qwack_boot.model.Role;

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

	public Personne getByIdWithVisites(Integer idPersonne) {
		return daoPersonne.findByIdwithVisites(idPersonne);
	}

	public Personne getByIdWithAdoptions(Integer idPersonne) {
		return daoPersonne.findByIdwithAdoptions(idPersonne);
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

	public void delete(Personne personne) {
		daoPersonne.deleteById(personne.getId());
	}

	public void deleteById(Integer id) {

		daoPersonne.deleteById(id);
	}

	public void faireDon(int idQuackShelter, double don) {
		QuackShelter quackShelter = quackSrv.getById(idQuackShelter);
		quackShelter.setTresorerie(quackShelter.getTresorerie() + don);
		quackSrv.update(quackShelter);
		System.out.println("La trésorerie du quackShelter s'élève maintenant a " + quackShelter.getTresorerie() + " €");

	}

	public List<Personne> getByRoleIn(List<Role> roles) {

		return daoPersonne.findByRoleIn(roles);
	}

	public Personne getByLogin(String login) {
		return daoPersonne.findByLogin(login);
	}

}
