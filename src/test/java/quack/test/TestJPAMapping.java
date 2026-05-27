package quack.test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quack.context.Singleton;

public class TestJPAMapping {

	public static void main(String[] args) {

		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		//emf.close();
		
		System.out.println(Singleton.getInstance().getDaoAnimal().findByDispo());

		System.out.println(Singleton.getInstance().getDaoAnimal().findByType("Chat"));
	}

}
