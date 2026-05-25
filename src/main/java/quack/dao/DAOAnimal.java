package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.Animal;

public class DAOAnimal implements IDAOAnimal {


	private EntityManager em;

	public void AnimalDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public Animal findById(Integer id) {
		return em.find(Animal.class, id);
	}

	@Override
	public List<Animal> findAll() {
		return em.createQuery("SELECT a FROM Animal a", Animal.class)
				.getResultList();
	}

	@Override
	public Animal save(Animal animal) {
		em.getTransaction().begin();
		em.persist(animal);
		em.getTransaction().commit();
		return animal;
	}

	@Override
	public Animal update(Animal animal) {
		em.getTransaction().begin();
		Animal updated = em.merge(animal);
		em.getTransaction().commit();
		return updated;
	}

	@Override
	public void deleteById(Integer id) {
		em.getTransaction().begin();
		Animal animal = em.find(Animal.class, id);
		if (animal != null) em.remove(animal);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Animal obj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Animal findByName(Animal String) {
		// TODO Auto-generated method stub
		return null;
	}

}


