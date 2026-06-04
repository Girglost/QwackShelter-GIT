package quack.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import quack.context.Singleton;
import quack.model.Patron;
import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Personne;
import quack.model.Visiteur;

public class DAOPersonne implements IDAOPersonne {

	@Override
	public Personne findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Personne personne = em.find(Personne.class, id);
		em.close();
		return personne;
	}

	@Override
	public List <Personne> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Personne> personnes = em.createQuery("from Personne").getResultList();
		em.close();
		return personnes;
	}
	
	@Override
	public List <Patron> findAllPatron() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Patron> patrons = em.createQuery("from Patron").getResultList();
		em.close();
		return patrons;
	}
	@Override
	public List <Visiteur> findAllVisiteur() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Visiteur> visiteurs = em.createQuery("from Visiteur").getResultList();
		em.close();
		return visiteurs;
	}
	
	@Override
	public List <Benevole> findAllBenevole() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Benevole> benevoles = em.createQuery("from Benevole").getResultList();
		em.close();
		return benevoles;
	}
	@Override
	public List <Employe> findAllEmploye() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Employe> employes = em.createQuery("from Employe").getResultList();
		em.close();
		return employes;
	}
	

	@Override
	public Personne save(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			em.persist(personne);
		em.getTransaction().commit();
		em.close();
		return personne;
	}
	
	// METHODE UPDATE A METTRE DANS LE SERVICE
	/*@Override
	public Personne update(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			Personne updated = em.merge(personne);
		em.getTransaction().commit();
		em.close();
		return updated;
	}
	*/
	
	public void delete(Personne personne) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
			personne = em.merge(personne);
			em.remove(personne);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public void deleteById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();	
		em.getTransaction().begin();
			Personne personne = em.find(Personne.class, id);
			em.remove(personne);
		em.getTransaction().commit();
	}
	
	@Override
	public Personne findByLoginAndPassword(String login, String password) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Personne personne =null;
		try 
		{
			personne = em.createQuery("SELECT p from Personne p where p.login=:login and p.password=:password",Personne.class)
					.setParameter("login", login)
					.setParameter("password", password)
					.getSingleResult();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		em.close();
		return personne;
	}
	



}
