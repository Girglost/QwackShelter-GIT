package qwack_boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qwack_boot.model.Personne;
import qwack_boot.model.Role;
import qwack_boot.model.StatutActivite;

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
	public Personne findByIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne")
	public Personne findByIdwithVisites(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne and p.role='VISITEUR'")
	public Personne findVisiteurByIdwithVisites(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.adoptions where p.id =:idPersonne and p.role='VISITEUR'")
	public Personne findVisiteurByIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne and p.role='BENEVOLE'")
	public Personne findBenevoleByIdwithVisites(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.adoptions where p.id =:idPersonne and p.role='BENEVOLE'")
	public Personne findBenevoleByIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne and p.role='PATRON'")
	public Personne findPatronByIdwithVisites(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.adoptions where p.id =:idPersonne and p.role='PATRON'")
	public Personne findPatronByIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.visites where p.id =:idPersonne and p.role='EMPLOYE'")
	public Personne findEmployeByIdwithVisites(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT p FROM Personne p LEFT JOIN FETCH p.adoptions where p.id =:idPersonne and p.role='EMPLOYE'")
	public Personne findEmployeByIdwithAdoptions(@Param("idPersonne") Integer idPersonne);

	public Personne findByLogin(String login);

	@Query("SELECT  p from Personne p where p.role='PATRON' and p.id=:idPersonne")
	public Optional<Personne> findPatronById(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT  p from Personne p where p.role='EMPLOYE' and p.id=:idPersonne")
	public Optional<Personne> findEmployeById(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT  p from Personne p where p.role='BENEVOLE' and p.id=:idPersonne")
	public Optional<Personne> findBenevoleById(@Param("idPersonne") Integer idPersonne);

	@Query("SELECT  p from Personne p where p.role='VISITEUR' and p.id=:idPersonne")
	public Optional<Personne> findVisiteurById(@Param("idPersonne") Integer idPersonne);

	public List<Personne> findByQuackShelterId(Integer id);

	public List<Personne> findByStatutActivite(StatutActivite statut);

	public List<Personne> findByAdmin(Boolean admin);

}
