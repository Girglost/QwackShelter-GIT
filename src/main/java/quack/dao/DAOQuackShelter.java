package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.Personne;
import quack.model.QuackShelter;

public class DAOQuackShelter implements IDAOQuackShelter {

	private EntityManager em;
	public void LieuDAO(EntityManager em) {
		this.em = em;
	}


	@Override
	public QuackShelter findById(Integer id) {
		return em.find(QuackShelter.class, id);
	}


	@Override
	public List <QuackShelter> findAll() {
		return em.createQuery("SELECT q FROM Personne q", QuackShelter.class).getResultList();
	}

	@Override
	public QuackShelter save(QuackShelter quack) {
		em.getTransaction().begin();
		em.persist(quack);
		em.getTransaction().commit();
		return quack;
	}

	@Override
	public QuackShelter update(QuackShelter quack) {
		em.getTransaction().begin();
		QuackShelter updated = em.merge(quack);
		em.getTransaction().commit();
		return updated;
	}


	@Override
	public void delete(Integer id) {
		em.getTransaction().begin();
		QuackShelter quack = em.find(QuackShelter.class, id);
		if (quack != null) em.remove(quack);
		em.getTransaction().commit();
	}
	
}
