package hu.bme.msc.agiletool.controller.project;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.model.Dashboard;
import hu.bme.msc.agiletool.model.Project;
import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.repository.DashboardRepository;
import hu.bme.msc.agiletool.repository.ProjectRepository;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ProjectController implements PredefineBaseController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/project/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findProjects(@RequestBody List<String> projects) {
        if (projects == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectRepository.findAll(projects),HttpStatus.OK);
    }

//    //get dashbord from projectid
//    @RequestMapping(value = "/project/resolve/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity returnWithExpandedProject(@PathVariable("id") String projectId){
//        if(projectId.isEmpty()){
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//
//        Project project = projectRepository.findOne(projectId);
//        Dashboard dashboard = dashboardRepository.findOne(project.getDashboardId());
//        //get backlog
//        dashboard.getBacklog();
//            //get all instance
//
//        //get todo
//            //get all instance
//
//        //get inprogress
//            //get all instance
//
//        //get done
//            //get all instance
//
//        throw  new RuntimeException("Not yet implemented");
//    }
//
//
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
