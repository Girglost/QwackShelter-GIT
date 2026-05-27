package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.context.Singleton;
import quack.model.Animal;
import quack.model.Genre;

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
	    EntityManager em = Singleton.getInstance().getEmf().createEntityManager();

	    List<Animal> animaux = em.createQuery(
	            "SELECT a FROM Animal a",Animal.class)
	            .getResultList();

	    em.close();

	    return animaux;
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
	public void delete(Animal animal) {
		em.getTransaction().begin();
		em.remove(animal);
		em.getTransaction().commit();
	}


	@Override
	public List<Animal> findByName(String name) {
	    return em.createQuery(
	            "SELECT a FROM Animal a WHERE a.nomAnimal = :name",
	            Animal.class)
	            .setParameter("name", name)
	            .getResultList();
	}

	@Override
	public List<Animal> findByGenre(Genre genre) {
	    return em.createQuery(
	            "SELECT a FROM Animal a WHERE a.genre = :genre",
	            Animal.class)
	            .setParameter("genre", genre)
	            .getResultList();
	}

	@Override
	public List<Animal> findByType(String type) {
	    return em.createQuery(
	            "FROM :type",
	            Animal.class)
	            .setParameter("type", type)
	            .getResultList();
	}

	@Override
	public List<Animal> findByDispo() {
	    return em.createQuery(
	            "SELECT a FROM Animal a JOIN statutsAnimal s WHERE s.dateDepart is null",
	            Animal.class)
	            .getResultList();
	}

}


