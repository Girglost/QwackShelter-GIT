package quack.JUnit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import quack.config.AppConfig;
import quack.model.Animal;
import quack.service.AnimalService;

@SpringJUnitConfig(AppConfig.class)
public class AnimalSrvTest {

	@Autowired
	AnimalService AnimalSrv;
	
	@Test
	void shouldLoadAnimal() {
		List<Animal> animals = AnimalSrv.getAll();
		
		
		Assertions.assertNotNull(animals);
		Assertions.assertFalse(animals.isEmpty());
		
	}
}
