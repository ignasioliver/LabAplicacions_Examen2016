package cat.tecnocampus;

import cat.tecnocampus.domain.Station;
import cat.tecnocampus.repositories.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FcGhackappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcGhackappBackendApplication.class, args);
	}

}
