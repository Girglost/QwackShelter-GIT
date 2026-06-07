package quack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Adresse;
import quack.model.Lieu;

public interface IDAOLieu extends JpaRepository <Lieu,Integer>{

	Lieu findByAdresse(Adresse adresse);
	
	
	  
}
