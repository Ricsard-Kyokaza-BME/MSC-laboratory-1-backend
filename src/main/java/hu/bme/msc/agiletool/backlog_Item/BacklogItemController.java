package hu.bme.msc.agiletool.backlog_Item;

import hu.bme.msc.agiletool.model.*;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.bson.types.ObjectId;
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

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BacklogItemController {
    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BugRepository bugRepository;



    /**
     * TODO Legyen elkülönítve a dev meg a production environment. Ki kell venni 2 ppropertybe a teljes elérési útvonalat.
     * */
//    protected boolean hasRole(Enum r) {
//        String role = r.toString();
//        // get security context from thread local
//        SecurityContext context = SecurityContextHolder.getContext();
//        if (context == null)
//            return false;
//
//        Authentication authentication = context.getAuthentication();
//        if (authentication == null)
//            return false;
//
//        for (GrantedAuthority auth : authentication.getAuthorities()) {
//            if (role.equals(auth.getAuthority()))
//                return true;
//        }
//
//        return false;
//    }


    /** TODO Kell minden utvonalra role beírás
     * USERSTORY Features here
     * */
    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    BacklogItem getUserStory(@PathVariable("id") String id) {
        return userStoryRepository.findOne(id);
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    List<UserStory> getAllUserStory() {
        return userStoryRepository.findAll();
    }

    @RequestMapping(value = "/userstory/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findUserStories(@RequestBody List<String> stories) {
        if (stories == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
//        userStoryRepository.findAll(stories);
        return new ResponseEntity<>(userStoryRepository.findAll(stories),HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO')")
    @ResponseBody
    ResponseEntity addUserStory(@RequestBody UserStory userStory) {
        if (userStory == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        userStory.setId(new ObjectId().toString());
        userStoryRepository.save(userStory);
        return new ResponseEntity<>(userStory,HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('PO')")
    @ResponseBody
    ResponseEntity updateUserStory(@PathVariable("id") String id, @RequestBody UserStory userStory) {
        if (userStory == null || id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        userStoryRepository.save(userStory);
        return new ResponseEntity<>(userStory,HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('PO')")
    @ResponseBody
    ResponseEntity deleteUserStory(@PathVariable("id") String id) {
        if (id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        userStoryRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }



    /**
     * TASK Features are here
     * */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    BacklogItem getTask(@PathVariable("id") String id) {
        return taskRepository.findOne(id);
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @RequestMapping(value = "/task/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findTasks(@RequestBody List<String> tasks) {
        if (tasks == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskRepository.findAll(tasks),HttpStatus.OK);
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity addTask(@RequestBody Task task) {
        if (task == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        task.setId(new ObjectId().toString());
        taskRepository.save(task);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        if (task == null || id.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        taskRepository.save(task);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity deleteTask(@PathVariable("id") String id) {
        if (id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        taskRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    /**
     * BUG Features are here
     * */
    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    BacklogItem getBug(@PathVariable("id") String id) {
        return bugRepository.findOne(id);
    }

    @RequestMapping(value = "/bug", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @RequestMapping(value = "/bug/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findBugs(@RequestBody List<String> bugs) {
        if (bugs == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bugRepository.findAll(bugs),HttpStatus.OK);
    }


    @RequestMapping(value = "/bug", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity addBug(@RequestBody Bug bug) {
        if (bug == null)
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bug.setId(new ObjectId().toString());
        bugRepository.save(bug);
        return new ResponseEntity<>(bug,HttpStatus.OK);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity updateBug(@PathVariable("id") String id, @RequestBody Bug bug) {
        if (bug == null || id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bugRepository.save(bug);
        return new ResponseEntity<>(bug,HttpStatus.OK);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity deleteBug(@PathVariable("id") String id) {
        if (id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bugRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }



    @RequestMapping(value = "/backlog-item", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    Map<String, List> getBug() {
        Map<String, List> allItem = new HashMap<>();
        allItem.put("userStory", userStoryRepository.findAll());
        allItem.put("task", taskRepository.findAll());
        allItem.put("bug", bugRepository.findAll());
        return allItem;
    }

    @RequestMapping(value = "/backlog-item/by-status", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    Map<String, Map<String, List<BacklogItem>>> getBacklogItemsByStatus() {
        Map<String, Map<String, List<BacklogItem>>> allItem = new HashMap<>();
        mapByStatus(userStoryRepository.findAll(), allItem, "userStory");
        mapByStatus(taskRepository.findAll(), allItem, "task");
        mapByStatus(bugRepository.findAll(), allItem, "bug");

        return allItem;
    }

    private void mapByStatus(List<? extends BacklogItem> items,
                             Map<String, Map<String, List<BacklogItem>>> target,
                             String subTarget) {
        Map<String, List<BacklogItem>> subMap = new HashMap<>();
        subMap.put("backlog", new ArrayList<>());
        subMap.put("todo", new ArrayList<>());
        subMap.put("inProgress", new ArrayList<>());
        subMap.put("done", new ArrayList<>());
        if(items != null) {
            for(BacklogItem item : items) {
                switch (item.getStatus()) {
                    case BACKLOG:
                        subMap.get("backlog").add(item);
                        break;
                    case TODO:
                        subMap.get("todo").add(item);
                        break;
                    case IN_PROGRESS:
                        subMap.get("inProgress").add(item);
                        break;
                    case DONE:
                        subMap.get("done").add(item);
                        break;
                    default:
                        break;
                }
            }

            target.put(subTarget, subMap);
        }
    }

}
