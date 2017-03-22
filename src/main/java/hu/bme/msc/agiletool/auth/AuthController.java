package hu.bme.msc.agiletool.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String login() {
        return "login";
    }

}
