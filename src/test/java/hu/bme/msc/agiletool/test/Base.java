package hu.bme.msc.agiletool.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base extends Preconditioning {
    @Test
    public void allowsAccessToRootResource() throws Exception {
        mvc.perform(get("/").
                accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)).
                andExpect(status().isOk()).
                andDo(log());
    }

    @Test
    public void allowsAccessToIsSignedIn() throws Exception {
        mvc.perform(get("/api/is-signed-in").
                with(user(testUserAlice)).
                accept(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print());
    }


}
