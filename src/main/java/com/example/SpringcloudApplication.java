package com.example;

import java.util.Collection;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@SpringBootApplication
public class SpringcloudApplication {

	@Bean
	CommandLineRunner commandLineRunner(
			ReservationRepository reservationRepository) {
		return string -> {
			Stream.of("Shahzad", "Gaurav", "Ram", "Neha", "Amit").forEach(
					firstName -> reservationRepository.save(new Reservation(
							firstName)));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudApplication.class, args);
	}
}

/*@Component
class DummyDataCLR implements CommandLineRunner {

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public void run(String... args) {
		Stream.of("Shahzad", "Gaurav", "Ram", "Neha", "Amit").forEach(
				firstName -> reservationRepository.save(new Reservation(
						firstName)));
	}
}*/

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@RestResource(path = "by-firstName")
	Collection<Reservation> findByFirstName(@Param("firstName") String firstName);
}

@Entity
class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;

	public Reservation(String firstName) {
		this.firstName = firstName;
	}

	
	public Reservation() {
	}
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", firstName=" + firstName + "]";
	}

}
