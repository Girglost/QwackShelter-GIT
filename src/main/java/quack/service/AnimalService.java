package quack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quack.dao.IDAOAnimal;
import quack.model.Animal;
import quack.model.Genre;

@Service
public class AnimalService {

	@Autowired
	IDAOAnimal daoAnimal;
	
	// --------------- CRUD ----------------
	
	public List<Animal> getAll()
	{
		return daoAnimal.findAll();
	}
	
	public Animal getById(Integer id) 
	{
		return daoAnimal.findById(id).orElse(null);
	}
	
	public void insert(Animal animal) 
	{
		daoAnimal.save(animal);
	}
	
	public void update(Animal animal) 
	{
		daoAnimal.save(animal);
	}

	public void delete(Integer id) 
	{
		daoAnimal.deleteById(id);
	}
	
	
	
	// --------------- PERSO ----------------
	
    public List<Animal> getByName(String name) {
        return daoAnimal.findByNomAnimal(name);
    }

    public List<Animal> getByGenre(Genre genre) {
        return daoAnimal.findByGenre(genre);
    }
    
    public List<Animal> getByType(String type){
    	return daoAnimal.findByFamille(type);
    }
    
    public List<Animal>	getDispoWithCaracteres(){
    	return daoAnimal.findByDispoWithCaracteres();
    }
    public Animal getByIdWithHistoriqueSante(Integer id){
    	return daoAnimal.findByIdWithHistoriqueSante(id);
    }
   
}
