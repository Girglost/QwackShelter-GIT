package quack.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import quack.context.Singleton;
import quack.dao.IDAOAnimal;
import quack.dao.IDAOLieu;
import quack.dao.IDAOPersonne;
import quack.dao.IDAOQuackShelter;
import quack.model.Benevole;
import quack.model.Canard;
import quack.model.Caractere;
import quack.model.Chat;
import quack.model.Chien;
import quack.model.Employe;
import quack.model.Famille;
import quack.model.Genre;
import quack.model.Lieu;
import quack.model.Patron;
import quack.model.Personne;
import quack.model.Poule;
import quack.model.QuackShelter;
import quack.model.Visiteur;

public class App {
	
	public static IDAOPersonne daoPersonne = Singleton.getInstance().getDaoPersonne();
	public static IDAOAnimal daoAnimal = Singleton.getInstance().getDaoAnimal();
	public static IDAOLieu daoLieu = Singleton.getInstance().getDaoLieu();
	public static IDAOQuackShelter daoQuackShelter = Singleton.getInstance().getDaoQuackShelter();
	
	public static  Lieu lieu1  = new Lieu("Shelter", "14", "Rue Qwack", "Nantes", "44100");
	public static Lieu lieu2  = new Lieu("Maison", "14", "Avenue Coin", "Paris", "75016");
	public static Lieu lieu3  = new Lieu("Appartement", "12", "Boulevard  du General Coin", "Paris", "75014");
	public static Lieu lieu4  = new Lieu("Appartement", "8", "Chemin du Coin", "Paris", "75008");
	
	public static QuackShelter quackshelter = new QuackShelter(10000, 5, lieu1);
	
	
		static Personne connected = null;
	
		public static int saisieInt(String message)
		{
			Scanner monScanner = new Scanner(System.in);
			System.out.println(message);
			return monScanner.nextInt();
		}

		public static double saisieDouble(String message)
		{
			Scanner monScanner = new Scanner(System.in);
			System.out.println(message);
			return monScanner.nextDouble();
		}

		public static String saisieString(String message)
		{
			Scanner monScanner = new Scanner(System.in);
			System.out.println(message);
			return monScanner.nextLine();
		}

		public static boolean saisieBoolean(String message)
		{
			Scanner monScanner = new Scanner(System.in);
			System.out.println(message);
			return monScanner.nextBoolean();
		}
		
		
		////////////////////////////////////////
		//------------PARTIE APP--------------//
		////////////////////////////////////////
		public static void menuPrincipal()
		{
			System.out.println("----BIENVENUE AU QUACK SHELTER-------");
			System.out.println("1 - Se connecter");
			System.out.println("2 - Devenir Visiteur");
			System.out.println("3 - Devenir Bénévole");
			System.out.println("4 - Stop");

			int choix = saisieInt("Choisir un menu");
			switch(choix)
			{
			case 1 : seConnecter();break;
			case 2 : devenirVisiteur();break;
			case 3 : devenirBenevole();break;
			case 4 : System.exit(0);break;
			}

			menuPrincipal();
		}
		
		public static void seConnecter() {
			String login = saisieString("Entrer votre Login");
			String password = saisieString("Entrer votre Mot de Passe");
			
			connected = daoPersonne.findByLoginAndPassword(login, password);
			System.out.println("Bonjour "+connected.getLogin()+" !");
			
			if(connected instanceof Patron) {
				System.out.println("Menu BOSS !");
			}
			
			if(connected instanceof Visiteur) {
				System.out.println("Menu Visiteur !");
			}
			if (connected instanceof Employe)
			{
				System.out.println("Menu Employe !");
				if(((Employe) connected).isAdmin()) {
					System.out.println("MENU EMPLOYE ADMIN");
				}
			}
			if (connected instanceof Benevole)
			{
				System.out.println("Menu Benevole !");
				if(((Benevole) connected).isAdmin()) {
					System.out.println("MENU BENEVOLE ADMIN");
				}
			}
		}
		
		public static void devenirVisiteur() {

			System.out.println("Creation d'un Compte Visiteur");
			String nom = saisieString("Entrer votre nom");
			String prenom = saisieString("Entrer votre prenom");
			String login = saisieString("Entrer votre login");
			String password = saisieString("Entrer votre mot de passe");

			int choixLieu = saisieInt("Vous avez dans : 1 - Une maison, 2 - Un appartement ?");
			String typeLieu = null;
			switch (choixLieu) {
			case 1:
				typeLieu = "Maison";
				break;
			case 2: 
				typeLieu = "Appartement";
				break;

			default:
				typeLieu = "Inconnu";
				break;
			}

			System.out.println("Entrer votre Adresse :");
			String numero = saisieString("Numero : ");
			String voie = saisieString("Voie : ");
			String ville = saisieString("Ville : ");
			String cp = saisieString("CP : ");



			Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
			daoLieu.save(habitation);
			System.out.println("Lieu ID "+ habitation);
			LocalDate dateInscription = LocalDate.now();

			Visiteur visiteur = new Visiteur(nom,prenom,login,password,habitation,dateInscription);

			daoPersonne.save(visiteur);

			System.out.println("Bonjour "+visiteur.getLogin()+", vous êtes maintenant Visiteur id ="+visiteur.getId());
		}
		public static void devenirBenevole() {
			
			System.out.println("Creation d'un Compte Bénévole");
			String nom = saisieString("Entrer votre nom");
			String prenom = saisieString("Entrer votre prenom");
			String login = saisieString("Entrer votre login");
			String password = saisieString("Entrer votre mot de passe");
			
			int choixLieu = saisieInt("Vous avez dans : 1 - Une maison, 2 - Un appartement ?");
			String typeLieu = null;
			switch (choixLieu) {
			case 1:
				typeLieu = "Maison";
				break;
			case 2: 
				typeLieu = "Appartement";
				break;

			default:
				typeLieu = "Inconnu";
				break;
			}
			
			/*System.out.println("Entrer votre Adresse :");
			String numero = saisieString("Numero : ");
			String voie = saisieString("Voie : ");
			String ville = saisieString("Ville : ");
			String cp = saisieString("CP : ");
			
			
			
			Lieu habitation = new Lieu(typeLieu, numero, voie, ville, cp);
			daoLieu.save(habitation);
			System.out.println("Lieu ID "+ habitation);*/
			LocalDate dateEngagement = LocalDate.now();
			
			Benevole benevole = new Benevole(nom, prenom,login,password, lieu2,
					false,dateEngagement,quackshelter);
			
			daoPersonne.save(benevole);
			//System.out.println("benevole ID "+ benevole);
			System.out.println("Bonjour "+benevole.getLogin()+", vous êtes maintenant bénévole !");
		}
		
		
		public static void menuPersonnel() {
			System.out.println("Menu Personnel");
			System.out.println("----------");
			System.out.println("1 - Accueillir un Animal");
			System.out.println("2 - Gérer une Adoption");
			System.out.println("3 - Gérer une Balade");
			
			int choix = saisieInt("");
			switch(choix) {
			case 1 :{
				System.out.println("Accueil d'un animal");
				break;
			}
			case 2 :{
				System.out.println("Gestion d'adoption");
				break;
			}
			case 3 :{
				System.out.println("Gestion de balade");
				break;
			}

			default:
				throw new IllegalArgumentException("Choix  incorrect : " + choix);
			}
		}
		
	public static void main(String[] args) {
		System.out.println("Bienvenue Chez Quack Shelter ! ");
		EntityManagerFactory emf = Singleton.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		
		/*Lieu lieu1  = new Lieu("Shelter", "14", "Rue Qwack", "Nantes", "44100");
		Lieu lieu2  = new Lieu("Maison", "14", "Avenue Coin", "Paris", "75016");
		Lieu lieu3  = new Lieu("Appartement", "12", "Boulevard  du General Coin", "Paris", "75014");
		Lieu lieu4  = new Lieu("Appartement", "8", "Chemin du Coin", "Paris", "75008");
		
		QuackShelter quackshelter = new QuackShelter(10000, 5, lieu1);*/
		
		
		Visiteur visiteur = new Visiteur("Yohann", "Yohann", "Yohann", "Yohann", lieu2,LocalDate.now());
		Patron patron = new Patron( "Ronan", "Ronan", "Ronan", "Ronan", lieu1);
		Employe employe = new Employe("Clea","Clea","Clea","Clea", lieu3,true, 800.5,LocalDate.now(),quackshelter);
		Benevole benevole = new Benevole("Marie","Marie","Marie","Marie", lieu4,true,LocalDate.now(),quackshelter);
		
		List<Caractere> caracteresChien = new ArrayList<>();
		
		caracteresChien.add(Caractere.Affecteux);
		caracteresChien.add(Caractere.Calin);
		caracteresChien.add(Caractere.Joueur);
		
		Chien chien = new Chien("Dog1", LocalDate.parse("2024-10-01"), "Blanc", "allégé",
				"Aucun", Famille.Canin, Genre.Male,caracteresChien , quackshelter,
				false, false,"Border Collie");
		
		List<Caractere> caracteresChat = new ArrayList<>();
		
		caracteresChat.add(Caractere.Affecteux);
		caracteresChat.add(Caractere.Joueur);
		
		Chat chat = new Chat("chat1", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				true, false,"Tigré");
		
		List<Caractere> caracteresCoin = new ArrayList<>();
		
		caracteresCoin.add(Caractere.Timide);
		caracteresCoin.add(Caractere.Craintif);
		
		Canard coin = new Canard("Coin1", LocalDate.parse("2025-12-01"), "Vert", "seche",
				"aucun", Famille.Galide, Genre.Male,caracteresChat , quackshelter,
				false, true, "CoinCoin", false);
		
		List<Caractere> caracteresPoule = new ArrayList<>();
		
		caracteresPoule.add(Caractere.Agressif);
		caracteresPoule.add(Caractere.Craintif);
		
		Poule poule = new Poule("Poulette", LocalDate.parse("2025-12-01"), "tigré noir", "prise de masse",
				"diabete", Famille.Felin, Genre.Femelle,caracteresChat , quackshelter,
				false, true, "CoinCoin", true);

		
		em.getTransaction().begin();
		em.persist(lieu1);
		em.persist(lieu2);
		em.persist(lieu3);
		em.persist(lieu4);
		em.persist(quackshelter);
		em.persist(visiteur);
		em.persist(patron);
		em.persist(employe);
		em.persist(benevole);
		em.persist(chien);
		em.persist(chat);
		em.persist(coin);
		em.persist(poule);
		
		em.getTransaction().commit();
		em.close();
		
		
		menuPrincipal();
		menuPersonnel();
		
	}

}
