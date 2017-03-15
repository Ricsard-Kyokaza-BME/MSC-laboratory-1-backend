package hu.bme.msc.agiletool.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.mongodb.util.JSON;
import hu.bme.msc.agiletool.auth.MongoDBAuthenticationProvider;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base {

    @Autowired
    private UserRepository repository;

    static final String PAYLOAD = "{\"firstName\": \"Alice\", \"username\": \"Alice\", " + "\"password\": \"abcd\"}";

    @Autowired
    WebApplicationContext context;
    @Autowired
    FilterChainProxy filterChain;
    @Autowired
    MongoDBAuthenticationProvider mongoDBAuthenticationProvider;

    MockMvc mvc;

    @Before
    public void setUp() {

        this.mvc = webAppContextSetup(context).addFilters(filterChain).build();

        SecurityContextHolder.clearContext();
    }

    @Test
    public void allowsAccessToRootResource() throws Exception {
        List<GrantedAuthority> aliceRoles = new ArrayList<>();
        aliceRoles.add(new SimpleGrantedAuthority("USER"));

        mvc.perform(get("/api/user").with(user(new User("alice", "abcd", aliceRoles))).
                accept(MediaTypes.HAL_JSON)).
                //andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print());

//        @Test
//        public void rejectsPostAccessToCollectionResource() throws Exception {
//            User alice = new User("Alice", null, "Alice", null, "abcd", null);
//            mvc.perform(post("/").
//                    content(PAYLOAD).
//                    accept(MediaTypes.HAL_JSON)).
//                    andExpect(status().isUnauthorized()).
//                    andDo(print());
//        }
    }

}
