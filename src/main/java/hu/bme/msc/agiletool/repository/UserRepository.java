package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
    public User findByUsername(String username);
    public User findById(String id);

}
