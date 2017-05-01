package hu.bme.msc.agiletool.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Dashboard extends Preconditioning {
    @Test
    public void fetchingAllDataFromDashboard() throws Exception {
        result = mvc.perform(
                get("/api/dashboard").
                        with(user(testUserKazi)).
                        with(csrf()).
                        accept(MediaTypes.HAL_JSON)).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
    }

//    @Test
//    public void postUserStoryToCollectionResource() throws Exception {
//        Map<Integer, String> backlog = new HashMap<>();
//        backlog.put(0, "alma");
//        String payload1 = backlog.toString();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);
//
//        mvc.perform(post("/api/dashboard").
//                with(user(testUserKazi)).
//                with(csrf()).
//                content(payload1).
//                headers(headers)).
//                andExpect(status().isCreated()).
//                andDo(print());
//
//
//
//    }
}
