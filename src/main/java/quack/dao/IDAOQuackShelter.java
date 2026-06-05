package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.QuackShelter;

public interface IDAOQuackShelter extends JpaRepository <QuackShelter,Integer>{

	
	public QuackShelter findById(Integer id);

	public List<QuackShelter> findAll();

	public QuackShelter save(QuackShelter quack); 

	public QuackShelter update(QuackShelter quack);

	public void delete(Integer id);
	
}
