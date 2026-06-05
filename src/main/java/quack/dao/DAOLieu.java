package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.context.Singleton;
import quack.model.Lieu;
import quack.model.Personne;

public class DAOLieu implements IDAOLieu{

	 
	 @Override
	    public Lieu findById(Integer id) {
		 EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	        return em.find(Lieu.class, id);
	    }
	 
	
	 @Override
	    public List<Lieu> findAll() {
		 EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	        return em.createQuery("SELECT l FROM Lieu l", Lieu.class).getResultList();
	    }
	 
	 @Override
	    public Lieu save(Lieu lieu) {
		 EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	        em.getTransaction().begin();
	        em.persist(lieu);
	        em.getTransaction().commit();
	        return lieu;
	    }
	 
	 
	 
	/* @Override
	    public Lieu update(Lieu lieu) {
		 EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	        em.getTransaction().begin();
	        Lieu updated = em.merge(lieu);
	        em.getTransaction().commit();
	        return updated;
	    }*/
	 
	 
	  @Override
	    public void deleteById(Integer id) {
		  EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
	        em.getTransaction().begin();
	        Lieu lieu = em.find(Lieu.class, id);
	        if (lieu != null) em.remove(lieu);
	        em.getTransaction().commit();
	    }


		public void delete(Lieu lieu) {
			EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
				lieu = em.merge(lieu);
				em.remove(lieu);
			em.getTransaction().commit();
			em.close();
		}


}
