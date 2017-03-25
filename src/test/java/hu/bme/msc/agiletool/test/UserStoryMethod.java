package hu.bme.msc.agiletool.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStoryMethod extends Preconditioning {

    @Test
    public void fetchingAllDataFromUserStory() throws Exception {
        result = mvc.perform(
                get("/api/userstory/search").
                        with(user(testUserAlice)).
                        with(csrf()).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(log()).
                andReturn();
    }

    @Test
    public void postUserStoryToCollectionResource() throws Exception {
        String payload1 = "{  \"title\" : \"story1\" }";
        String payload2 = "{  \"title\" : \"story2\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);

        mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload1).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(log());

        mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload2).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(log());

    }


    @Test
    public void fetching2DataFromUserStory() throws Exception {
        String payload1 = "{  \"title\" : \"story1\" }";
        String payload2 = "{  \"title\" : \"story2\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);

        result = mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload1).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(log()).
                andReturn();
        String a = result.getResponse().getHeader("Location").replaceAll("http://.*/api/userstory/", "");

        result = mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload2).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(log()).
                andReturn();
        String b = result.getResponse().getHeader("Location").replaceAll("http://.*/api/userstory/", "");


        mvc.perform(get("/api/userstory/search/findByTitleContainingIgnoreCase?title=ST").
                with(user(testUserKazi)).
                with(csrf()).
                headers(headers)).
                andExpect(status().isOk()).
                andDo(log());
    }
}
