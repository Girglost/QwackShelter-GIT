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
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		HistoriqueSante historique = em.find(HistoriqueSante.class, id);
		em.close();
		return historique;
	}

	@Override
	public List<HistoriqueSante> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<HistoriqueSante> historiques = em.createQuery("from HistoriqueSante", HistoriqueSante.class).getResultList();
		em.close();
		return historiques;
	}

	@Override
	public HistoriqueSante save(HistoriqueSante historique) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.merge(historique);
		em.getTransaction().commit();
		em.close();
		return historique;
	}

	@Override
	public void delete(HistoriqueSante historique) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		historique = em.merge(historique);
		em.remove(historique);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		HistoriqueSante historique = em.find(HistoriqueSante.class, id);
		em.remove(historique);
		em.getTransaction().commit();
		em.close();
	}

}
