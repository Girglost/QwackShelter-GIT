package quack.test;

import org.springframework.beans.factory.annotation.Autowired;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOModule;
import quest.model.Filiere;
import quest.model.Matiere;
import quest.service.MatiereService;

public class TestSpringJPA {

	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired
	MatiereService matiereSrv;
	
	
	@Autowired
	IDAOModule daoModule;
	public void run() 
	{
		System.out.println("ALLLO");
		for(Filiere f : daoFiliere.findAll()) 
		{
			System.out.println(f);
		}
		
		
		for(Matiere m  : matiereSrv.getAll()) 
		{
			System.out.println(m);
		}
		
		
		
		System.out.println(daoModule.findByFiliereId(1));
	}
}
