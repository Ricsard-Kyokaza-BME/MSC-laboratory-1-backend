package hu.bme.msc.agiletool.test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStoryMethod extends Preconditioning {

    @Test
    public void fetchingAllDataFromUserStory() throws Exception {
        result = mvc.perform(
                get("/api/userstory").
                        with(user(testUserAlice)).
                        with(csrf()).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }
//    @Test
//    public void asd() throws Exception {
//        result = mvc.perform(
//                get("/api/userstory/58d66332f624775baa852611").
//                        with(user(testUserAlice)).
//                        with(csrf()).
//                        accept(MediaTypes.HAL_JSON)).
//                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
//                andExpect(status().isOk()).
//                andDo(print()).
//                andReturn();
//    }

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
                andDo(print());
    }


    @Test
    public void preformPostToFetchListOfUserStory() throws Exception {
        ArrayList<String> asd = new ArrayList<>();
        asd.add("58d65b5df6247753c0c110a2");
        asd.add("58d65b5df6247753c0c110a3");
        String payload = asd.toString();
        JSONArray o = new JSONArray(asd);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);

        result = mvc.perform(post("/api/userstory/find").
                with(user(testUserKazi)).
                with(csrf()).
                content(o.toString()).
                headers(headers)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }
}
