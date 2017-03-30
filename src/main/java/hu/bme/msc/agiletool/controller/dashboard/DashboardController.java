package hu.bme.msc.agiletool.controller.dashboard;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.model.*;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.DashboardRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
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
        if (dashboards == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dashboardRepository.findAll(dashboards),HttpStatus.OK);
    }
    

    @RequestMapping(value = "/dashboard/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO', 'USER')")
    @ResponseBody
    ResponseEntity updateDashboardWithAdditionalBacklogItem(@PathVariable("id") String id, @RequestBody BacklogItem backlogItem){
        if (backlogItem == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        Dashboard dashboard = dashboardRepository.findOne(id);

        Map<Integer, String> adding = dashboard.getBacklog();
        if (adding == null){
            throw new RuntimeException("Getting dashboard from path variable failed.");
        }

        switch (backlogItem.getType()){
            case BUG:
                Bug bug = bugRepository.save((Bug) backlogItem);

                adding.put(dashboard.getBacklog().size()+1,bug.getId());
                dashboard.setBacklog(adding);
                break;
            case TASK:
                Task task = taskRepository.save((Task) backlogItem);

                adding.put(dashboard.getBacklog().size()+1,task.getId());
                dashboard.setBacklog(adding);
                break;
            case USER_STORY:
                UserStory userStory = userStoryRepository.save((UserStory) backlogItem);

                adding.put(dashboard.getBacklog().size()+1,userStory.getId());
                dashboard.setBacklog(adding);
                break;
            default:
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST );
        }
        dashboardRepository.save(dashboard);
        return new ResponseEntity<>(dashboard,HttpStatus.OK);
    }
}
