package bnb.pulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "bnb.pulse.dao")
public class PulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PulseApplication.class, args);
	}

}
