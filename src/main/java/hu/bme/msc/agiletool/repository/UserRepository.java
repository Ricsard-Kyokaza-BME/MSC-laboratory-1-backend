package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestResource(path = "user", collectionResourceRel = "user")
public interface UserRepository extends MongoRepository<User, String> {

    List<User>  findById(@RequestBody List<String> userIds);
    List<User>  findByFirstName(@Param("firstName") String firstName);
    List<User>  findByLastName(@Param("lastName") String lastName);
    User        findByUsername(@Param("username") String username);
    User        findByUsernameContaining(@Param("username") String username);

//    List<User>  findByFullNameContainingIgnorCase(@Param("keyword") String keyword);
    List<User>  findByFirstNameOrLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);
    List<User>  findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("firstName")String firstName, @Param("lastName")String lastName);


}
