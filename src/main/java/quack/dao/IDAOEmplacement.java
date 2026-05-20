package quack.dao;

import java.util.List;

import quack.model.Animal;
import quack.model.Emplacement;

public interface IDAOEmplacement extends IDAO<Emplacement,Integer> {

	public Emplacement findById(Integer id);

	public List<Emplacement> findAll();

	public Emplacement save(Emplacement emplacement); 

	public Emplacement update(Emplacement emplacement);

	public void deleteById(Integer id);

}
