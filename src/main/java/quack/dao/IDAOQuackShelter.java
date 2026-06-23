package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quack.model.Animal;
import quack.model.QuackShelter;

public interface IDAOQuackShelter extends JpaRepository<QuackShelter,Integer>{

/*
	//methode pour ajouter un personnel
	public void ajouterPersonnel(Personnel personnel);

	//methode pour recuperer les animaux du refuge
	
	public List<Animal> findByRefugeId(Integer idRefuge);
	
	//methode pour compter les animaux dans le refuge 
	
	public int countByRefugeNotNull();
	
	//methode pour verifier le nombre de plavces dans le refuge
	//public boolean aDesPlacesDisponibles();
*/

	
}
