package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOPersonne;
import quack.model.Personne;
@Service
public class PersonneService {
	@Autowired
	IDAOPersonne daoPersonne;
	
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
		daoPersonne.save(personne);
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
