package quack.dao;

import java.util.List;

import quack.model.HistoriqueSante;

public interface IDAOHistoriqueSante {

	
	public HistoriqueSante findById(Integer id);

	public List<HistoriqueSante> findAll();

	public HistoriqueSante save(HistoriqueSante historique); 

	public HistoriqueSante update(HistoriqueSante historique);

	public void delete(Integer id);
	
}
