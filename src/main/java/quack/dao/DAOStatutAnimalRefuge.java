package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.QuackShelter;

public class DAOStatutAnimalRefuge implements IDAOStatutAnimalRefuge {

	private EntityManager em;
	public void LieuDAO(EntityManager em) {
		this.em = em;
	}


	@Override
	public StatutAnimalRefuge findById(Integer id) {
		return em.find(StatutAnimalRefuge.class, id);
	}


	@Override
	public List <StatutAnimalRefuge> findAll() {
		return em.createQuery("SELECT s FROM StatutAnimalRefuge s", StatutAnimalRefuge.class).getResultList();
	}

	@Override
	public StatutAnimalRefuge save(StatutAnimalRefuge statut) {
		em.getTransaction().begin();
		em.persist(statut);
		em.getTransaction().commit();
		return statut;
	}

	@Override
	public StatutAnimalRefuge update(StatutAnimalRefuge statut) {
		em.getTransaction().begin();
		StatutAnimalRefuge updated = em.merge(statut);
		em.getTransaction().commit();
		return updated;
	}


	@Override
	public void delete(Integer id) {
		em.getTransaction().begin();
		QuackShelter quack = em.find(StatutAnimalRefuge.class, id);
		if (statut != null) em.remove(statut);
		em.getTransaction().commit();
	}
	
	
}
