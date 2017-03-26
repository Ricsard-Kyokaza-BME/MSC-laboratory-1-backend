package hu.bme.msc.agiletool.controller.project;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.model.*;
import hu.bme.msc.agiletool.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hu.bme.msc.agiletool.controller.BacklogItemController.mapByStatus;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ProjectController implements PredefineBaseController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BugRepository bugRepository;

    @RequestMapping(value = "/project/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findProjects(@RequestBody List<String> projects) {
        if (projects == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectRepository.findAll(projects),HttpStatus.OK);
    }

    //get dashbord from projectid
    @RequestMapping(value = "/project/resolve/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    Map<String, Map<String, List<BacklogItem>>> returnWithExpandedProject(@PathVariable("id") String projectId){
        if(projectId.isEmpty()){
            return null;
        }

        Map<String, Map<String, List<BacklogItem>>> allItem = new HashMap<>();
        String subTarget = null;

        Project project = projectRepository.findOne(projectId);
        Dashboard dashboard = dashboardRepository.findOne(project.getDashboardId());
        //get backlog
        Map<Integer, String> backlogToResolve = dashboard.getBacklog();
        Map<Integer, String> todoToResolve = dashboard.getTodo();
        Map<Integer, String> inProgressToResolve = dashboard.getInprogress();
        Map<Integer, String> doneToResolve = dashboard.getDone();

        ArrayList<BacklogItem> items = new ArrayList<>();
        for (Integer i : backlogToResolve.keySet()){
            if(userStoryRepository.findOne(backlogToResolve.get(i))!=null){
                items.add(userStoryRepository.findOne(backlogToResolve.get(i)));
                subTarget="userStory";
            }else if (taskRepository.findOne(backlogToResolve.get(i))!=null){
                items.add(taskRepository.findOne(backlogToResolve.get(i)));
                subTarget="task";
            }else if(bugRepository.findOne(backlogToResolve.get(i))!=null){
                items.add(bugRepository.findOne(backlogToResolve.get(i)));
                subTarget="bug";
            }

        }
        for (Integer i : todoToResolve.keySet()){
            if(userStoryRepository.findOne(todoToResolve.get(i))!=null){
                items.add(userStoryRepository.findOne(todoToResolve.get(i)));
                subTarget="userStory";
            }else if (taskRepository.findOne(todoToResolve.get(i))!=null){
                items.add(taskRepository.findOne(todoToResolve.get(i)));
                subTarget="task";

            }else if(bugRepository.findOne(todoToResolve.get(i))!=null){
                items.add(bugRepository.findOne(todoToResolve.get(i)));
                subTarget="bug";

            }

        }
        for (Integer i : inProgressToResolve.keySet()){
            if(userStoryRepository.findOne(inProgressToResolve.get(i))!=null){
                items.add(userStoryRepository.findOne(inProgressToResolve.get(i)));
                subTarget="userStory";

            }else if (taskRepository.findOne(inProgressToResolve.get(i))!=null){
                items.add(taskRepository.findOne(inProgressToResolve.get(i)));
                subTarget="task";

            }else if(bugRepository.findOne(inProgressToResolve.get(i))!=null){
                items.add(bugRepository.findOne(inProgressToResolve.get(i)));
                subTarget="bug";

            }

        }
        for (Integer i : doneToResolve.keySet()){
            if(userStoryRepository.findOne(doneToResolve.get(i))!=null){
                items.add(userStoryRepository.findOne(doneToResolve.get(i)));
                subTarget="userStory";

            }else if (taskRepository.findOne(doneToResolve.get(i))!=null){
                items.add(taskRepository.findOne(doneToResolve.get(i)));
                subTarget="task";

            }else if(bugRepository.findOne(doneToResolve.get(i))!=null){
                items.add(bugRepository.findOne(doneToResolve.get(i)));
                subTarget="bug";

            }

        }

            mapByStatus(items, allItem, subTarget);

        return allItem;
    }

    //TODO A dashboard null, ha a projek ujonnan van létrehozva. Need to FIX
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity addProjectAndModifieUsersProject(@RequestBody Project projectFromRequest){
        if(projectFromRequest == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        projectRepository.save(projectFromRequest);
        Project project = projectRepository.findOne(projectFromRequest.getId());

        for (String iterInProjectUserIds : project.getUsersInProject()) {
            User actualUser = userRepository.findOne(iterInProjectUserIds);
                ArrayList<String> userProjList = actualUser.getProjects();
                userProjList.add(project.getId());
                actualUser.setProjects(userProjList);
            userRepository.save(actualUser);
        }

        return new ResponseEntity<>(project,HttpStatus.OK);
    }

}
