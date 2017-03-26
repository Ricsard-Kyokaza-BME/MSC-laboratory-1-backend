package hu.bme.msc.agiletool.auth;

import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String login() {
        return "login";
    }


    @RequestMapping(value = "/api/is-signed-in", method = RequestMethod.GET)
    @ResponseBody
    User isSignedIn(Principal principal) {
        return userRepository.findByUsername(principal.getName());
    }

}
