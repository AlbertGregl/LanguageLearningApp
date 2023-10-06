package pi.project.languageApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("algebra.hr.dal.entity")
@EnableJpaRepositories("algebra.hr.dal.repository")
@ComponentScan(basePackages = {"pi.project.languageApp", "hr.algebra.utils", "algebra.hr.dal","algebra.hr.bll"})
public class LanguageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguageAppApplication.class, args);
	}
}
