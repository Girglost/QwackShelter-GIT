package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.Emplacement;

public class DAOEmplacement implements IDAOEmplacement{

	private EntityManager em;

	public void AnimalDAO(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Emplacement findById(Integer id) {
		return em.find(Emplacement.class, id);
	}

	@Override
	public List<Emplacement> findAll() {
		return em.createQuery("SELECT e FROM Emplacement e", Emplacement.class).getResultList();
	}

	@Override
	public Emplacement save(Emplacement emplacement) {
		em.getTransaction().begin();
		em.persist(emplacement);
		em.getTransaction().commit();
		return emplacement;
	}

	@Override
	public Emplacement update(Emplacement emplacement) {
		em.getTransaction().begin();
		Emplacement updated = em.merge(emplacement);
		em.getTransaction().commit();
		return updated;
	}

	@Override
	public void deleteById(Integer id) {
		em.getTransaction().begin();
		Emplacement emplacement = em.find(Emplacement.class, id);
		if (emplacement != null) em.remove(emplacement);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Emplacement obj) {
		// TODO Auto-generated method stub
		
	}


}
