package hu.bme.msc.agiletool;

import hu.bme.msc.agiletool.auth.AuthRequest;
import hu.bme.msc.agiletool.auth.AuthResponse;
import hu.bme.msc.agiletool.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloWorld {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String login() {
        return "login";
    }

    @RequestMapping(value = "/is-signed-in", method = RequestMethod.GET)
    @ResponseBody
    Principal isSignedIn(Principal principal) {
        return principal;
    }

    @RequestMapping("/secure")
    @ResponseBody
    User secure() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return new User("Alice", "Smith", "alice", "a@a.com", "abcd", roles);
    }

}
