package qwack_boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import qwack_boot.model.Cause;
import qwack_boot.model.HistoriqueSante;

public interface IDAOHistoriqueSante extends JpaRepository<HistoriqueSante, Integer> {

	public List<HistoriqueSante> findByCause(Cause cause);

}
