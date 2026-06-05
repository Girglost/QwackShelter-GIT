package quack.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import quack.config.AppConfig;

public class SpringApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(TestSpringJPA.class).run();
		ctx.close();
	}

}
