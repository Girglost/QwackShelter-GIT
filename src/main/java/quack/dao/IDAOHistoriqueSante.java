package quack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Cause;
import quack.model.HistoriqueSante;

public interface IDAOHistoriqueSante extends JpaRepository<HistoriqueSante,Integer> {

	public List<HistoriqueSante> findByCause(Cause cause);
	
	
}
