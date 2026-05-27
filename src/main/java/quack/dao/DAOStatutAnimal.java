package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.context.Singleton;
import quack.model.StatutAnimal;


public class DAOStatutAnimal implements IDAOStatutAnimal {

	
	@Override
	public StatutAnimal findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		StatutAnimal statut = em.find(StatutAnimal.class, id);
		em.close();
		return statut;
	}


	@Override
	public List <StatutAnimal> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<StatutAnimal> statut = em.createQuery("from StatutAnimal").getResultList();
		em.close();
		return statut;
	}

	@Override
	public StatutAnimal save(StatutAnimal statut) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.merge(statut);
		em.getTransaction().commit();
		return statut;
	}

	@Override
	public void delete(StatutAnimal statut) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		statut = em.merge(statut);
		em.remove(statut);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		StatutAnimal statut = em.find(StatutAnimal.class, id);
		em.remove(statut);
		em.getTransaction().commit();
		em.close();
	}





}
