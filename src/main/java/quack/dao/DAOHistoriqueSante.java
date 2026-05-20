package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.HistoriqueSante;

public class DAOHistoriqueSante implements IDAOHistoriqueSante{

	
	private EntityManager em;

	public void AnimalDAO(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public HistoriqueSante findById(Integer id) {
		return em.find(HistoriqueSante.class, id);
	}

	@Override
	public List<HistoriqueSante> findAll() {
		return em.createQuery("SELECT h FROM HistoriqueSante h", HistoriqueSante.class).getResultList();
	}

	@Override
	public HistoriqueSante save(HistoriqueSante historique) {
		em.getTransaction().begin();
		em.persist(historique);
		em.getTransaction().commit();
		return historique;
	}

	@Override
	public HistoriqueSante update(HistoriqueSante historique) {
		em.getTransaction().begin();
		HistoriqueSante updated = em.merge(historique);
		em.getTransaction().commit();
		return updated;
	}

	@Override
	public void delete(Integer id) {
		em.getTransaction().begin();
		HistoriqueSante historique = em.find(HistoriqueSante.class, id);
		if (historique != null) em.remove(historique);
		em.getTransaction().commit();
	}
	
}
