package pi.project.languageApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
https://www.baeldung.com/spring-boot-singleton-vs-beans

In the Spring Framework, beans are singleton by default. When you define a bean, typically using the @Bean annotation in
a configuration class or using @Component and similar stereotypes (@Service, @Repository, @Controller),
Spring creates a single instance of that bean by default.
This instance is stored in the Spring container and is reused across the application context.

I have also setup lazy in the properties file..
spring.main.lazy-initialization=true
*/

@SpringBootApplication
@EntityScan("algebra.hr.dal.entity")
@EnableJpaRepositories("algebra.hr.dal.repository")
@ComponentScan(basePackages = {"pi.project.languageApp", "hr.algebra.utils", "algebra.hr.dal","algebra.hr.bll"})
public class LanguageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguageAppApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
