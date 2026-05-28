package quack.test;

import java.util.Scanner;

import jakarta.persistence.EntityManagerFactory;
import quack.context.Singleton;

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

	
	
	
	public static void main(String[] args) {
		System.out.println("Bienvenue Chez Quack Shelter ! ");
		
		EntityManagerFactory emf = Singleton.getInstance().getEmf();
		
		emf.close();
	}

}
