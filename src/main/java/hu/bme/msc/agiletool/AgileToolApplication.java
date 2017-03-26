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

		List<GrantedAuthority> aliceRoles = new ArrayList<>();
		aliceRoles.add(new SimpleGrantedAuthority("USER"));
		repository.save(new User("Alice", "Smith", "alice", "a@a.com", "abcd", aliceRoles, null));

		List<GrantedAuthority> johnRoles = new ArrayList<>();
		johnRoles.add(new SimpleGrantedAuthority("PO"));
		johnRoles.add(new SimpleGrantedAuthority("USER"));
		repository.save(new User("John", "Doe", "john", "b@b.com", "abcd", johnRoles, null));

		List<GrantedAuthority> kaziRoles = new ArrayList<>();
		kaziRoles.add(new SimpleGrantedAuthority("PO"));
		kaziRoles.add(new SimpleGrantedAuthority("USER"));
		repository.save(new User("Kazi", "Marci", "kazi", "kazi@gmail.com.com", "kazi", kaziRoles, null));

	}
}
