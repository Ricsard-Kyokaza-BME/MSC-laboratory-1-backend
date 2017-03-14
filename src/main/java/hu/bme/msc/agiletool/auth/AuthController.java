package hu.bme.msc.agiletool.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String login() {
        return "login";
    }

    @RequestMapping(value = "/is-signed-in", method = RequestMethod.GET)
    @ResponseBody
    Principal isSignedIn(Principal principal) {
        return principal;
    }

}
