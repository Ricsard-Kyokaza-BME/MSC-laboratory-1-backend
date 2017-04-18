package hu.bme.msc.agiletool.controller.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.model.*;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.DashboardRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/dashboard/{id}/add", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('PO', 'USER')")
    @ResponseBody
    ResponseEntity updateDashboardWithAdditionalBacklogItem(
            @PathVariable("id") String dashboardId,
            @RequestBody String backlogItem
    ) throws IOException {
        if (backlogItem.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        JSONObject jObjBacklogItem = new JSONObject(backlogItem);
        Dashboard dashboard = dashboardRepository.findOne(dashboardId);

        if (dashboard == null) {
            throw new RuntimeException("Getting dashboard from path variable failed.");
        }

        ObjectMapper mapper = new ObjectMapper();
        if (jObjBacklogItem.get("type").toString().equals("0")) {
            UserStory userStoryRawObject = mapper.readValue(backlogItem, UserStory.class);
            UserStory userStory = userStoryRepository.save(userStoryRawObject);

            putBacklogItemToDashboard(jObjBacklogItem, dashboard, userStory, userStoryRawObject);
        } else if (jObjBacklogItem.get("type").toString().equals("1")) {
            Task taskRawObject = mapper.readValue(backlogItem, Task.class);
            Task task = taskRepository.save(taskRawObject);

            putBacklogItemToDashboard(jObjBacklogItem, dashboard, task, taskRawObject);
        } else if (jObjBacklogItem.get("type").toString().equals("2")) {
            Bug bugRawObject = mapper.readValue(backlogItem, Bug.class);
            Bug bug = bugRepository.save(bugRawObject);

            putBacklogItemToDashboard(jObjBacklogItem, dashboard, bug, bugRawObject);
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        dashboard = dashboardRepository.save(dashboard);

        return new ResponseEntity<>(dashboard, HttpStatus.OK);
    }

    private void putBacklogItemToDashboard(JSONObject jObjBacklogItem, Dashboard dashboard,
                                           BacklogItem backlogItem, BacklogItem rawObject) throws IOException {
        if (jObjBacklogItem.has("id")) {
            dashboard.removeItem(backlogItem.getId());
        }

        switch (rawObject.getStatus()) {
            case BACKLOG:
                dashboard.addToBacklog(backlogItem.getId());
                break;
            case TODO:
                dashboard.addToTodo(backlogItem.getId());
                break;
            case IN_PROGRESS:
                dashboard.addToInProgress(backlogItem.getId());
                break;
            case DONE:
                dashboard.addToDone(backlogItem.getId());
        }
    }
}
