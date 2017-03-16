package hu.bme.msc.agiletool.test;


import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                get("/api/user/search/findByLastName?lastName=Doe").
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
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
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


//    @Test
//    public void fetchingUserByFullNameContainingIgnoreCase() throws Exception {
//        result = mvc.perform(
//                get("/api/user/search/findByFullName?keyword=JohnDoe").
////                        with(user(testUserAlice)).
//                        accept(MediaTypes.HAL_JSON)).
////                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
////                andExpect(status().isOk()).
//                andDo(print()).
//                andReturn();
//    }

}
