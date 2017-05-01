package hu.bme.msc.agiletool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AgileToolApplication extends SpringBootServletInitializer {

//	@Autowired
//	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AgileToolApplication.class, args); //NOSONAR
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		repository.deleteAll();
//
//		List<GrantedAuthority> aliceRoles = new ArrayList<>();
//		aliceRoles.add(new SimpleGrantedAuthority("USER"));
//		repository.save(new User("Alice", "Smith", "alice", "a@a.com", "abcd", aliceRoles, new ArrayList<>()));
//
//		List<GrantedAuthority> johnRoles = new ArrayList<>();
//		johnRoles.add(new SimpleGrantedAuthority("PO"));
//		johnRoles.add(new SimpleGrantedAuthority("USER"));
//		repository.save(new User("John", "Doe", "john", "b@b.com", "abcd", johnRoles, new ArrayList<>()));
//
//		List<GrantedAuthority> kaziRoles = new ArrayList<>();
//		kaziRoles.add(new SimpleGrantedAuthority("PO"));
//		kaziRoles.add(new SimpleGrantedAuthority("USER"));
//		repository.save(new User("Kazi", "Marci", "kazi", "kazi@gmail.com.com", "kazi", kaziRoles, new ArrayList<>()));
//
//	}
}
