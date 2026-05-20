package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.StatutAnimal;
import quack.model.StatutAnimal;

public class DAOStatutAnimalRefuge implements IDAOStatutAnimalRefuge {

	private EntityManager em;
	public void LieuDAO(EntityManager em) {
		this.em = em;
	}


	@Override
	public StatutAnimal findById(Integer id) {
		return em.find(StatutAnimal.class, id);
	}


	@Override
	public List <StatutAnimal> findAll() {
		return em.createQuery("SELECT s FROM StatutAnimalRefuge s", StatutAnimal.class).getResultList();
	}

	@Override
	public StatutAnimal save(StatutAnimal statut) {
		em.getTransaction().begin();
		em.persist(statut);
		em.getTransaction().commit();
		return statut;
	}

	@Override
	public StatutAnimal update(StatutAnimal statut) {
		em.getTransaction().begin();
		StatutAnimal updated = em.merge(statut);
		em.getTransaction().commit();
		return updated;
	}


	@Override
	public void delete(Integer id) {
		em.getTransaction().begin();
		StatutAnimal statut = em.find(StatutAnimal.class, id);
		if (statut != null) em.remove(statut);
		em.getTransaction().commit();
	}
	
	
}
