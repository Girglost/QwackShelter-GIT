package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Animal;
import quack.model.Personnel;
import quack.model.QuackShelter;

public interface IDAOQuackShelter extends JpaRepository<QuackShelter,Integer>{


	//methode pour ajouter un personnel
	public void ajouterPersonnel(Personnel personnel);

	//methode pour recuperer les animaux du refuge
	public List<Animal> getAnimauxDuRefuge();

	//methode pour verifier le nombre de plavces dans le refuge
	public boolean aDesPlacesDisponibles();

}
