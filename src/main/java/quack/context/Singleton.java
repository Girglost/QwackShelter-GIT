package quack.context;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quack.dao.DAOAnimal;
import quack.dao.DAOEmplacement;
import quack.dao.DAOHistoriqueSante;
import quack.dao.DAOLieu;
import quack.dao.DAOPersonne;
import quack.dao.DAOQuackShelter;
import quack.dao.DAOStatutAnimalRefuge;
import quack.dao.IDAOAnimal;
import quack.dao.IDAOEmplacement;
import quack.dao.IDAOHistoriqueSante;
import quack.dao.IDAOLieu;
import quack.dao.IDAOPersonne;
import quack.dao.IDAOQuackShelter;
import quack.dao.IDAOStatutAnimalRefuge;

public class Singleton {
	//Avec le polymorphisme, on pourra changer facilement de DAO au module JPA
	//DAOVisite deviendra DAOVisiteJPA, mais le reste de l'appli fonctionnera toujours !

	private IDAOPersonne daoPersonne = new DAOPersonne();
	private IDAOAnimal daoAnimal = new DAOAnimal();
	private IDAOEmplacement daoEmplacement = new DAOEmplacement();
	private IDAOHistoriqueSante daoHistoriqueSante = new DAOHistoriqueSante();
	private IDAOLieu daoLieu = new DAOLieu();
	private IDAOQuackShelter daoQuackShelter = new DAOQuackShelter();
	private IDAOStatutAnimalRefuge daoStatutAnimalRefuge = new DAOStatutAnimalRefuge();


	/*private PatientService patientSrv = new PatientService();
		private CompteService compteSrv = new CompteService();
		private VisiteService visiteSrv = new VisiteService();*/

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("quackShelter");


	private static Singleton instance=null;


	//Impossible de creer un objet Singleton en dehors de cette page
	//Seule la methode getInstance() peut declencher ce constructeur
	private Singleton() {}


	//On est sur que l'application retournera toujours le même objet "instance"
	//Si c'est la 1ere fois que la methode est call, instance est initialise avec new Singleton();
	//Sinon, retourne toujours la meme instance
	public static Singleton getInstance() 
	{
		if(instance==null) //1er passage dans getInstance si instance est toujours null
		{
			instance=new Singleton();
		}
		return instance;
	}



	public IDAOPersonne getDaoPersonne() {
		return daoPersonne;
	}
	public IDAOAnimal getDaoAnimal() {
		return daoAnimal;
	}
	public IDAOEmplacement getDaoEmplacement() {
		return daoEmplacement;
	}
	public IDAOHistoriqueSante getDaoHistoriqueSante() {
		return daoHistoriqueSante;
	}
	public IDAOLieu getDaoLieu() {
		return daoLieu;
	}
	public IDAOQuackShelter getDaoQuackShelter() {
		return daoQuackShelter;
	}
	public IDAOStatutAnimalRefuge getDaoStatutAnimalRefuge() {
		return daoStatutAnimalRefuge;
	}
	/*public PatientService getPatientSrv() {
			return patientSrv;
		}
		public CompteService getCompteSrv() {
			return compteSrv;
		}
		public VisiteService getVisiteSrv() {
			return visiteSrv;
		}*/


	public EntityManagerFactory getEmf() {
		return emf;
	}








}


