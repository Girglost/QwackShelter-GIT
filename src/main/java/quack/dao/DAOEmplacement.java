package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.context.Singleton;
import quack.model.Emplacement;

public class DAOEmplacement implements IDAOEmplacement{

	private EntityManager em;

	public void AnimalDAO(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Emplacement findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Emplacement emplacement = em.find(Emplacement.class, id);
		em.close();
		return emplacement;
	}

	@Override
	public List<Emplacement> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Emplacement> emplacements = em.createQuery("FROM Emplacement", Emplacement.class).getResultList();
		em.close();
		return emplacements;
	}

	@Override
	public Emplacement save(Emplacement emplacement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.merge(emplacement);
		em.getTransaction().commit();
		em.close();
		return emplacement;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Emplacement emplacement = em.find(Emplacement.class, id);
		em.remove(emplacement);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Emplacement emplacement) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		emplacement = em.merge(emplacement);
		em.remove(emplacement);
		em.getTransaction().commit();
		em.close();
	}


}
