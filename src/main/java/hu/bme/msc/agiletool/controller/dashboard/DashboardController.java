package hu.bme.msc.agiletool.controller.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.model.Dashboard;
import hu.bme.msc.agiletool.model.Task;
import hu.bme.msc.agiletool.model.UserStory;
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
        JSONObject jObjBacklogItem = new JSONObject(backlogItem);

        if (backlogItem.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        Dashboard dashboard = dashboardRepository.findOne(dashboardId);
//        Map<Integer, String> adding = dashboard.getBacklog();
        if (dashboard == null) {
            throw new RuntimeException("Getting dashboard from path variable failed.");
        }

        ObjectMapper mapper = new ObjectMapper();
        if(jObjBacklogItem.get("type").toString().equals("0")){
            UserStory UserStoryRawObject = mapper.readValue(backlogItem, UserStory.class);
            UserStory userStory = userStoryRepository.save(UserStoryRawObject);

            if(jObjBacklogItem.has("id")) {
                dashboard.removeItem(userStory.getId());
            }
            switch (UserStoryRawObject.getStatus()){
                case BACKLOG:
                    dashboard.addToBacklog(userStory.getId());
                    break;
                case TODO:
                    dashboard.addToTodo(userStory.getId());
                    break;
                case IN_PROGRESS:
                    dashboard.addToInProgress(userStory.getId());
                    break;
                case DONE:
                    dashboard.addToDone(userStory.getId());
            }

//            adding.put(dashboard.getBacklog().size() + 1, userStory.getId());
//            dashboard.setBacklog(adding);
        }else if(jObjBacklogItem.get("type").toString().equals("1")){
            Task TaskRawObject = mapper.readValue(backlogItem, Task.class);
            Task task = taskRepository.save(TaskRawObject);


            if(jObjBacklogItem.has("id")) {
                dashboard.removeItem(task.getId());
            }
            switch (TaskRawObject.getStatus()){
                case BACKLOG:
                    dashboard.addToBacklog(task.getId());
                    break;
                case TODO:
                    dashboard.addToTodo(task.getId());
                    break;
                case IN_PROGRESS:
                    dashboard.addToInProgress(task.getId());
                    break;
                case DONE:
                    dashboard.addToDone(task.getId());
            }

//            adding.put(dashboard.getBacklog().size() + 1, task.getId());
//            dashboard.setBacklog(adding);
        }else if(jObjBacklogItem.get("type").toString().equals("2")){
            Bug BugRawObject = mapper.readValue(backlogItem, Bug.class);
            Bug bug = bugRepository.save(BugRawObject);

            if(jObjBacklogItem.has("id")) {
                dashboard.removeItem(bug.getId());
            }
            switch (BugRawObject.getStatus()){
                case BACKLOG:
                    dashboard.addToBacklog(bug.getId());
                    break;
                case TODO:
                    dashboard.addToTodo(bug.getId());
                    break;
                case IN_PROGRESS:
                    dashboard.addToInProgress(bug.getId());
                    break;
                case DONE:
                    dashboard.addToDone(bug.getId());
            }

//            adding.put(dashboard.getBacklog().size() + 1, bug.getId());
//            dashboard.setBacklog(adding);
        }else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        dashboardRepository.save(dashboard);
        dashboard = dashboardRepository.findOne(dashboardId);
        return new ResponseEntity<>(dashboard, HttpStatus.OK);
    }
}
