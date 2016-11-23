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

//    @Autowired
//    AuthenticationManager authenticationManager;

//    @ResponseBody
//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public AuthResponse mosLogin(@RequestBody AuthRequest loginRequest, HttpServletRequest request) {
//        AuthResponse response = null;
//
//        try {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//            token.setDetails(new WebAuthenticationDetails(request));
//
//            Authentication auth = authenticationManager.authenticate(token);
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            securityContext.setAuthentication(auth);
//
//            if(auth.isAuthenticated()){
//                HttpSession session = request.getSession(true);
//                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//                AuthResponse loginResponse = new AuthResponse("Ok");
//                response = loginResponse;
//            }else{
//                SecurityContextHolder.getContext().setAuthentication(null);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return response;
//    }

//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String login() {
        return "login";
    }

    @RequestMapping(value = "/is-signed-in", method = RequestMethod.GET)
    @ResponseBody
    Principal isSignedIn(Principal principal) {
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("USER"));
//        return new User("Alice", "Smith", "alice", "a@a.com", "abcd", roles);
        return principal;
    }

//    @RequestMapping("/login")
//    @ResponseBody
//    User login() {
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("USER"));
//        return new User("Alice", "Smith", "alice", "a@a.com", "abcd", roles);
//    }

//    @Secured({"USER"})
//    @RequestMapping("/secured")
//    @ResponseBody
//    String secured() {
//        return "Secured!";
//    }
//
//    @Secured({"USER"})
    @RequestMapping("/secure")
    @ResponseBody
    User secure() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return new User("Alice", "Smith", "alice", "a@a.com", "abcd", roles);
    }

}
