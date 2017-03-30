package hu.bme.msc.agiletool.controller.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.msc.agiletool.model.*;
import org.json.JSONObject;
import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.DashboardRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DashboardController implements PredefineBaseController {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    BugRepository bugRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserStoryRepository userStoryRepository;

    @RequestMapping(value = "/dashboard/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findDashboards(@RequestBody List<String> dashboards) {
        if (dashboards == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dashboardRepository.findAll(dashboards), HttpStatus.OK);
    }


    @RequestMapping(value = "/dashboard/{id}", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('PO', 'USER')")
    @ResponseBody
    ResponseEntity updateDashboardWithAdditionalBacklogItem(
            @PathVariable("id") String dashboardId,
            @RequestBody String backlogItem
    ) throws IOException {
        JSONObject jObjBacklogItem = new JSONObject(backlogItem);

        if (backlogItem.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        Dashboard dashboard = dashboardRepository.findOne(dashboardId);
        Map<Integer, String> adding = dashboard.getBacklog();

        if (adding == null) {
            throw new RuntimeException("Getting dashboard from path variable failed.");
        }

        ObjectMapper mapper = new ObjectMapper();
        if(jObjBacklogItem.get("type").toString().equals("0")){
            UserStory UserStoryRawObject = mapper.readValue(backlogItem, UserStory.class);
            UserStory userStory = userStoryRepository.save(UserStoryRawObject);

            adding.put(dashboard.getBacklog().size() + 1, userStory.getId());
            dashboard.setBacklog(adding);
        }else if(jObjBacklogItem.get("type").toString().equals("1")){
            Task TaskRawObject = mapper.readValue(backlogItem, Task.class);
            Task task = taskRepository.save(TaskRawObject);

            adding.put(dashboard.getBacklog().size() + 1, task.getId());
            dashboard.setBacklog(adding);
        }else if(jObjBacklogItem.get("type").toString().equals("2")){
            Bug BugRawObject = mapper.readValue(backlogItem, Bug.class);
            Bug bug = bugRepository.save(BugRawObject);

            adding.put(dashboard.getBacklog().size() + 1, bug.getId());
            dashboard.setBacklog(adding);
        }else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        dashboardRepository.save(dashboard);
        return new ResponseEntity<>(dashboard, HttpStatus.OK);
    }
}
