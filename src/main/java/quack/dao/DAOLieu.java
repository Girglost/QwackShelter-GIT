package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.model.Lieu;

public class DAOLieu implements IDAOLieu{

	 private EntityManager em;
	 public void LieuDAO(EntityManager em) {
	        this.em = em;
	    }
	
	 
	 @Override
	    public Lieu findById(int id) {
	        return em.find(Lieu.class, id);
	    }
	 
	 
	 @Override
	    public List<Lieu> findAll() {
	        return em.createQuery("SELECT l FROM Lieu l", Lieu.class).getResultList();
	    }
	 
	 @Override
	    public Lieu save(Lieu lieu) {
	        em.getTransaction().begin();
	        em.persist(lieu);
	        em.getTransaction().commit();
	        return lieu;
	    }
	 
	 @Override
	    public Lieu update(Lieu lieu) {
	        em.getTransaction().begin();
	        Lieu updated = em.merge(lieu);
	        em.getTransaction().commit();
	        return updated;
	    }
	 
	 
	  @Override
	    public void delete(Integer id) {
	        em.getTransaction().begin();
	        Lieu lieu = em.find(Lieu.class, id);
	        if (lieu != null) em.remove(lieu);
	        em.getTransaction().commit();
	    }
	  

}
