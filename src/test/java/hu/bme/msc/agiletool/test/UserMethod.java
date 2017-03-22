package hu.bme.msc.agiletool.test;


import hu.bme.msc.agiletool.model.User;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMethod extends Preconditioning {


    @Test
    public void fetchingAllDataFromUser() throws Exception {
        result = mvc.perform(
                get("/api/user").
                        with(user(testUserAlice)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }

    @Test
    public void fetchingAllLinksFromUser() throws Exception {
        result = mvc.perform(
                get("/api/user/search").
                        with(user(testUserAlice)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }

    @Test
    public void fetchingUserByFindByUsername() throws Exception {
        result = mvc.perform(
                get("/api/user/search/findByUsername?username=" + testUserJohn.getUsername()).
                        with(user(testUserJohn)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(log()).
                andReturn();
    }

    @Test
    public void fetchingUserByFirstName() throws Exception {
        result = mvc.perform(
                get("/api/user/search/findByFirstName?firstName=John").
                        with(user(testUserJohn)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(log()).
                andReturn();
    }


    @Test
    public void fetchingUserByLastName() throws Exception {
        result = mvc.perform(
                get("/api/user/search/findByLastName").
                        param("lastName", "Doe").
                        with(user(testUserKazi)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }


    @Test
    public void fetchingUserById() throws Exception {
        fetchingUserByLastName();
        JSONObject jsonObject = jsonParse(result);
        String userUriFromResponse = jsonObject.
                getJSONObject("_embedded").
                getJSONArray("user").
                getJSONObject(0).
                getJSONObject("_links").
                getJSONObject("user").
                get("href").toString().replaceAll("http://.*/api", "/api");

        result = mvc.perform(
                get(userUriFromResponse).
                        with(user(testUserKazi)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }


    @Test
    public void fetchingUserByFirstNameOrLastName() throws Exception {
        result = mvc.perform(
                get("/api/user/search/findByFirstNameOrLastName?firstName=John&lastName=Smith").
                        with(user(testUserAlice)).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();

        JSONObject jsonObject = jsonParse(result);
        Integer userCountFromResponse = jsonObject.
                getJSONObject("_embedded").
                getJSONArray("user").length();

        assertEquals(Integer.valueOf(2), userCountFromResponse);
    }

    @Test
    public void postUserToCollectionResource() throws Exception {

        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("PO"));
        //roles.add(new SimpleGrantedAuthority("USER"));
        //User ricsi = new User("ricsi", "balog", "ricsi", null, "ricsi", roles );
//        org.springframework.security.core.userdetails.User ricsi = generateTestUser("ricsi", "ricsi", "PO", "USER");
        String payload = "{  \"firstName\" : \"ricsi\",  \"lastName\" : \"balog\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
//        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("ollie:gierke").getBytes())));


        mvc.perform(post("/api/user").
                with(user(testUserKazi)).
                with(csrf()).
//                content(ricsi.toString()).
                content(payload).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(print());
    }
}
