package quack.dao;

import java.util.List;

import quack.model.Lieu;

public interface IDAOLieu {
	 
	    public Lieu findById(int id);
 	 
	    public List<Lieu> findAll();
      	
	    public Lieu save(Lieu lieu);
	
	    public Lieu update(Lieu lieu);
	   
	    public void delete(Integer id);
	  
}
