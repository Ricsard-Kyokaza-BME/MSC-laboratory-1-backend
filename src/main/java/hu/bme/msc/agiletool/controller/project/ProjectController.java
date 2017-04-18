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

    @RequestMapping(value = "/project/{id}/dashboard", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity returnDashboardToProject(@PathVariable("id")  String projectId){
        if(projectId.isEmpty()){
            return new ResponseEntity<Dashboard>(HttpStatus.BAD_REQUEST);
        }

        Project project = projectRepository.findOne(projectId);
        Dashboard dashboard = dashboardRepository.findOne(project.getDashboardId());

//        DashboardResolving dashboardResolvingRetval = new DashboardResolving();
        Map<String, ArrayList> dashboardResolvingRetvalWithList = new HashMap<>();
        for (Map.Entry<String, Map<Integer, String>> dashBoardEntrys : dashboard.getAllCollectionsFromDashboard().entrySet()) {
            String typeInTheDashboardCollection = dashBoardEntrys.getKey();


//            Map<Integer, BacklogItem> addingItemsToDashboard = new HashMap<>();
            ArrayList<BacklogItem> biArrayList = new ArrayList<>();
            for (Map.Entry<Integer, String> dashboardItemIdEntry : dashBoardEntrys.getValue().entrySet()) {
                Integer dashboardItemPosition = dashboardItemIdEntry.getKey();
                String dashboardItemId = dashboardItemIdEntry.getValue();
                // ...
//                switch (typeInTheDashboardCollection){
//                    case "backlog":
//
//                        break;
//                    case "todo":
//                        break;
//                    case "inprogress":
//                        break;
//                    case "done":
//                        break;
//                }
//                dashboardResolvingRetval.add(typeInTheDashboardCollection, resolveTheItem(addingItemsToDashboard, dashboardItemPosition, dashboardItemId));
                dashboardResolvingRetvalWithList.put(typeInTheDashboardCollection, resolveTheItem(biArrayList, dashboardItemId));
            }

        }

        return new ResponseEntity<>(dashboardResolvingRetvalWithList, HttpStatus.BAD_REQUEST);
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
        Project project = projectRepository.findOne(projectId);
        Dashboard dashboard = dashboardRepository.findOne(project.getDashboardId());

        Map<Integer, String> backlogToResolve = dashboard.getBacklog();
        Map<Integer, String> todoToResolve = dashboard.getTodo();
        Map<Integer, String> inProgressToResolve = dashboard.getInprogress();
        Map<Integer, String> doneToResolve = dashboard.getDone();

        ArrayList<BacklogItem> userstory = new ArrayList<>();
        ArrayList<BacklogItem> bug = new ArrayList<>();
        ArrayList<BacklogItem> task = new ArrayList<>();

        iterateRepo(backlogToResolve, userstory, bug, task);
        iterateRepo(todoToResolve, userstory, bug, task);
        iterateRepo(inProgressToResolve, userstory, bug, task);
        iterateRepo(doneToResolve, userstory, bug, task);

        mapByStatus(userstory, allItem, "userStory");
        mapByStatus(task, allItem, "task");
        mapByStatus(bug, allItem, "bug");

        return allItem;
    }

    private void iterateRepo(Map<Integer, String> map, ArrayList<BacklogItem> userstory, ArrayList<BacklogItem> bug,
                             ArrayList<BacklogItem> task) {
        for (Integer i : map.keySet()){
            if(userStoryRepository.findOne(map.get(i))!=null){
                userstory.add(userStoryRepository.findOne(map.get(i)));
            }else if (taskRepository.findOne(map.get(i))!=null){
                task.add(taskRepository.findOne(map.get(i)));
            }else if(bugRepository.findOne(map.get(i))!=null){
                bug.add(bugRepository.findOne(map.get(i)));
            }
        }
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity addProjectAndModifieUsersProject(@RequestBody Project projectFromRequest){
        if(projectFromRequest == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        if(projectFromRequest.getDashboardId() != null && !projectFromRequest.getDashboardId().isEmpty()){
            projectRepository.save(projectFromRequest);
        }else {
            Dashboard d = dashboardRepository.save(new Dashboard());
            projectFromRequest.setDashboardId(d.getId());
            projectRepository.save(projectFromRequest);
        }
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

    private Map<Integer, BacklogItem> resolveTheItem(Map<Integer, BacklogItem> addingItemsToDashboard, Integer dashboardItemPosition, String dashboardItemId){
        if(userStoryRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.put(dashboardItemPosition, userStoryRepository.findOne(dashboardItemId));
        }else if (taskRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.put(dashboardItemPosition, taskRepository.findOne(dashboardItemId));
        }else if(bugRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.put(dashboardItemPosition, bugRepository.findOne(dashboardItemId));
        }
        return addingItemsToDashboard;
    }

    private ArrayList<BacklogItem> resolveTheItem(ArrayList<BacklogItem> addingItemsToDashboard, String dashboardItemId){
        if(userStoryRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.add(userStoryRepository.findOne(dashboardItemId));
        }else if (taskRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.add(taskRepository.findOne(dashboardItemId));
        }else if(bugRepository.findOne(dashboardItemId)!=null){
            addingItemsToDashboard.add(bugRepository.findOne(dashboardItemId));
        }
        return addingItemsToDashboard;
    }
}
