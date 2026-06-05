package quack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.HistoriqueSante;

public interface IDAOHistoriqueSante extends JpaRepository<HistoriqueSante,Integer> {

	
}
