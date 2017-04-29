package hu.bme.msc.agiletool.auth;

import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();

        user.setUsername(connection.fetchUserProfile().getId());
        user.setFirstName(connection.fetchUserProfile().getFirstName());
        user.setLastName(connection.fetchUserProfile().getLastName());
        user.setEmail(connection.fetchUserProfile().getEmail());
        user.addRole("USER", "PO");
        user.setPassword("abcd");

        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
        return user.getUsername();
    }
}
