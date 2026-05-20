package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.StatutAnimal;
import quest.context.Singleton;
import quest.model.Filiere;


public class DAOStatutAnimalRefuge implements IDAOStatutAnimalRefuge {

	private EntityManager em;
	public void LieuDAO(EntityManager em) {
		this.em = em;
	}


	@Override
	public StatutAnimal findById(Integer id) {
		StatutAnimal statut = em.find(StatutAnimal.class, id);
		em.close();
		return statut;

	}


	@Override
	public List <StatutAnimal> findAll() {
		List<StatutAnimal> statut = em.createQuery("from StatutAnimal").getResultList();
		em.close();
		return statut;
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
		statut = em.merge(statut);
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
		em.getTransaction().begin();
		StatutAnimal statut = em.find(StatutAnimal.class, id);
		em.remove(statut);
		em.getTransaction().commit();
		em.close();
	}


}
