package hu.bme.msc.agiletool;

import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AgileToolApplication implements CommandLineRunner {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AgileToolApplication.class, args); //NOSONAR
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("USER"));
		// save a couple of customers
		repository.save(new User("Alice", "Smith", "alice", "a@a.com", "abcd", roles));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User user : repository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (User user : repository.findByLastName("Smith")) {
			System.out.println(user);
		}

	}
}
