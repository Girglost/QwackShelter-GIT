package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.Personne;

public class DAOPersonne implements IDAOPersonne {


	private EntityManager em;
	public void LieuDAO(EntityManager em) {
		this.em = em;
	}


	@Override
	public Personne findById(Integer id) {
		return em.find(Personne.class, id);
	}


	@Override
	public List <Personne> findAll() {
		return em.createQuery("SELECT p FROM Personne p", Personne.class).getResultList();
	}

	@Override
	public Personne save(Personne personne) {
		em.getTransaction().begin();
		em.persist(personne);
		em.getTransaction().commit();
		return personne;
	}

	@Override
	public Personne update(Personne personne) {
		em.getTransaction().begin();
		Personne updated = em.merge(personne);
		em.getTransaction().commit();
		return updated;
	}


	@Override
	public void delete(Integer id) {
		em.getTransaction().begin();
		Personne personne = em.find(Personne.class, id);
		if (personne != null) em.remove(personne);
		em.getTransaction().commit();
	}

}
