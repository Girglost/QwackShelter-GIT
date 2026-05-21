package quack.test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPAMapping {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		
		emf.close();

	}

}
