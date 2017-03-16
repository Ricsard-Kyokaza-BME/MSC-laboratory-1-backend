package hu.bme.msc.agiletool.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChain;

    private MockMvc mvc;
    private MvcResult result;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(context)
                .addFilters(filterChain)
                .build();
        SecurityContextHolder.clearContext();
    }


    @Test
    public void allowsAccessToRootResource() throws Exception {
        mvc.perform(get("/").
                accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)).
                andExpect(status().isOk()).
                andDo(log());
    }

    @Test
    public void fetchingAllDataFromUser() throws Exception {
        List<GrantedAuthority> aliceRoles = new ArrayList<>();
        aliceRoles.add(new SimpleGrantedAuthority("USER"));

        result = mvc.perform(get("/api/user").
                with(user(new User("alice", "abcd", aliceRoles))).
                accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }
}
