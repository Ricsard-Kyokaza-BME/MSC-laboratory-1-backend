package hu.bme.msc.agiletool.test;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class Preconditioning {
    @Autowired
    WebApplicationContext context;
    @Autowired
    FilterChainProxy filterChain;

    MockMvc mvc;
    MvcResult result;

    User testUserAlice = generateTestUser("alice", "abcd", "USER");
    User testUserJohn = generateTestUser("john", "abcd", "USER", "PO");
    User testUserKazi = generateTestUser("kazi", "kazi", "USER", "PO");

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(context)
                .addFilters(filterChain)
                .build();
        SecurityContextHolder.clearContext();
    }

    User generateTestUser(String username, String password, String... roles){
        User generatedUser;

        List<GrantedAuthority> rolesToSet = new ArrayList<>();
        for (String roleItem: roles) {
            rolesToSet.add(new SimpleGrantedAuthority(roleItem));
        }

        generatedUser = new User(username, password, rolesToSet);

        return generatedUser;
    }
}
