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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                andDo(print()).
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
                andDo(print());

        mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload2).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(print());

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
                andDo(print()).
                andReturn();
        String a = result.getResponse().getHeader("Location").replaceAll("http://.*/api/userstory/", "");

        result = mvc.perform(post("/api/userstory").
                with(user(testUserKazi)).
                with(csrf()).
                content(payload2).
                headers(headers)).
                andExpect(status().isCreated()).
                andDo(print()).
                andReturn();
        String b = result.getResponse().getHeader("Location").replaceAll("http://.*/api/userstory/", "");

//        JSONObject asd = new JSONObject();
//        List<String> alma = new ArrayList<>();
//        alma.add(a);
//        alma.add(b);
//        JSONObject qqq = new JSONObject();
//        qqq.append("userstoryIds", alma);
//        asd.append("data", qqq);
        //"userstoryIds=" + a +","+bs
//        result = mvc.perform(
//                post("").
//                        with(user(testUserAlice)).
//                        with(csrf()).
//                        content(" \"userstoryIds\" : \"1\"").
//                        accept(MediaTypes.HAL_JSON)).
////                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
////                andExpect(status().isOk()).
//                andDo(print()).
//                andReturn();

        System.out.println(b);
        System.out.println(b.substring(3,4));

        mvc.perform(get("/api/userstory/search/findByTitleContainingIgnoreCase?title=ST").
                with(user(testUserKazi)).
                with(csrf()).
                headers(headers)).
//                andExpect(status().isOk()).
                andDo(print());
    }
}
