package quack.test;

import java.time.LocalDate;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import quack.context.Singleton;
import quack.model.Benevole;
import quack.model.Employe;
import quack.model.Lieu;
import quack.model.Patron;
import quack.model.Visiteur;

public class App {
	//------------PARTIE APP--------------//
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

	
		public static void menuPrincipal() {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Se Connecter");
			System.out.println("2 - S'inscrire");
			int choix_menu = saisieInt("");
			switch (choix_menu) {
			case 1: {
				
				System.out.println("Connexion en cours d'implémentation...");
				break;
			}
			case 2:{
				System.out.println(" 1 - Devenir Visiteur");
				System.out.println(" 2 - Devenir Patron");
				System.out.println(" 3 - Devenir Employe");
				System.out.println(" 4 - Devenir Benevole");
				int choixTypeCompte = saisieInt("");
				inscription(choixTypeCompte);
				
			}
			default:
				menuPrincipal();
			}
		}
	
		public static void inscription(int choixTypeCompte) {
			System.out.println("Creation du compte");
			EntityManagerFactory emf = Singleton.getInstance().getEmf();
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			switch(choixTypeCompte) {
			case 1 :{
				System.out.println("Visiteur");
				Visiteur personne = new Visiteur("Doe", "John", "Doe", "John", null,LocalDate.now());
				
				em.persist(personne);
				
				System.out.println("Nouveau Visiteur inscrit !");
				break;
			}
			case 2 :{
				System.out.println("Patron");
				Patron personne = new Patron( "Doe", "Jane", "Doe", "Jane", null);
				em.persist(personne);
				System.out.println("Nouveau Patron inscrit !");
				break;
			}
			case 3 :{
				System.out.println("Employe");
				Employe personne = new Employe("Clea","Clea","Clea","Clea", null,true, 800.5,LocalDate.now());
				em.persist(personne);
				System.out.println("Nouveau Employe inscrit !");
				break;
			}
			case 4 :{
				System.out.println("Benevole");
				Benevole personne = new Benevole("Clea","Clea","Clea","Clea", null,true,LocalDate.now());
				em.persist(personne);
				System.out.println("Nouveau Benevole inscrit !");
				break;
			}
			default:
				throw new IllegalArgumentException("Choix Compte incorrect : " + choixTypeCompte);
			}
			
			em.getTransaction().commit();
			emf.close();
		}
	public static void main(String[] args) {
		System.out.println("Bienvenue Chez Quack Shelter ! ");
		menuPrincipal();
		
		

	}

}
