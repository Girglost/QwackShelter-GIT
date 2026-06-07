package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOLieu;
import quack.dao.IDAOPersonne;
import quack.model.Adresse;
import quack.model.Lieu;
import quack.model.Personne;
@Service
public class PersonneService {
	@Autowired
	IDAOPersonne daoPersonne;
	@Autowired
	IDAOLieu daoLieu;
	
	public List<Personne> getAll()
	{
		return daoPersonne.findAll();
	}
	
	public Personne getById(Integer id) 
	{
		return daoPersonne.findById(id).orElse(null);
	}
	
	public Personne getByLoginAndPassword(String login,String password) 
	{
		return daoPersonne.findByLoginAndPassword(login, password);
	}
	
	public void insert(Personne personne) 
	{
		//Permet d'insert un Lieu en cascade si il n'est pas  en bdd au moment de la creation de la personne
		Lieu lieuPersonne = personne.getHabitation();
		System.out.println("Adresse de la personne "+lieuPersonne);
		String typeLieu = personne.getHabitation().getType();
		Adresse adresse = personne.getHabitation().getAdresse();
		
		Lieu lieu = daoLieu.findByAdresse(adresse);
		System.out.println("Lieu trouvé "+lieu);
		if(lieu ==null) {
			
			lieu = new Lieu(typeLieu,adresse.getNumero(),adresse.getVoie(),adresse.getVille(),adresse.getCp());
			daoLieu.save(lieu);
			//System.out.println(lieu);
			personne.setHabitation(lieu);
		}
		//System.out.println(personne);
		personne = daoPersonne.save(personne);
	}
	
	public void update(Personne personne) 
	{
		daoPersonne.save(personne);
	}
	
	public void delete(Personne personne) 
	{
		daoPersonne.deleteById(personne.getId());
	}
	
	public void deleteById(Integer id) 
	{
		daoPersonne.deleteById(id);
	}
}
