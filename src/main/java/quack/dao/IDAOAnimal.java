package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quack.model.Animal;
import quack.model.Genre;
import quack.model.Statut;

public interface IDAOAnimal extends JpaRepository<Animal,Integer> {

	
	public List<Animal> findByNomAnimal(String name);

	public List<Animal> findByGenre(Genre genre);

	public List<Animal> findByFamille(String type);
	
	@Query("SELECT a FROM Animal a LEFT JOIN FETCH a.caracteres WHERE a.statutAnimal.dateDepart is null")
	public List<Animal>	findByDispoWithCaracteres();
	@Query("SELECT a FROM Animal a LEFT JOIN FETCH a.historiqueSante WHERE a.id=:id")
	public Animal findByIdWithHistoriqueSante(@Param("id")Integer idAnimal);
	@Query("SELECT a FROM Animal a WHERE a.statutAnimal.statut=:statut")
	public List<Animal> findByStatut(@Param("statut")Statut statut);
}
