package qwack_boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qwack_boot.model.Personne;
import qwack_boot.model.Role;

public interface IDAOPersonne extends JpaRepository<Personne, Integer> {

	// LOGIN UNIQUE => Si exist en bdd, on peut pas le créer
	public boolean existsByLogin(String login);

	@Query("SELECT  p from Personne p where p.role='PATRON'")
	public List<Personne> findAllPatron();

	@Query("SELECT  p from Personne p where p.role='VISITEUR'")
	public List<Personne> findAllVisiteur();

	@Query("SELECT  p from Personne p where p.role='EMPLOYE'")
	public List<Personne> findAllEmploye();

	@Query("SELECT  p from Personne p where p.role='BENEVOLE'")
	public List<Personne> findAllBenevole();

	public List<Personne> findByRoleIn(List<Role> roles);

	public Personne findByLoginAndPassword(String login, String password);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.adoptions where p.id =:idPersonne")
	public Personne findbyIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne")
	public Personne findbyIdwithVisites(@Param("idPersonne") Integer idPersonne);

}
