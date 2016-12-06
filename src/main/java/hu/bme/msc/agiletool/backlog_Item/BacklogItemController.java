package hu.bme.msc.agiletool.backlog_Item;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.model.Task;
import hu.bme.msc.agiletool.model.UserStory;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BacklogItemController {
    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BugRepository bugRepository;


    /**
     * USERSTORY Features here
     * */
    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.GET)
    @ResponseBody
    BacklogItem getUserStory(@PathVariable("id") String id) {
        return userStoryRepository.findOne(id);
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
    @ResponseBody
    List<UserStory> getAllUserStory() {
        return userStoryRepository.findAll();
    }

    @RequestMapping(value = "/userstory/find", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity findUserStories(@RequestBody List<String> stories) {
        if (stories == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

//        userStoryRepository.findAll(stories);
        return new ResponseEntity<>(userStoryRepository.findAll(stories),HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.POST)
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
    @ResponseBody
    BacklogItem getTask(@PathVariable("id") String id) {
        return taskRepository.findOne(id);
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    @ResponseBody
    List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @RequestMapping(value = "/task/find", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity findTasks(@RequestBody List<String> tasks) {
        if (tasks == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskRepository.findAll(tasks),HttpStatus.OK);
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
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
    @ResponseBody
    ResponseEntity updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        if (task == null || id.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        taskRepository.save(task);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
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
    @ResponseBody
    BacklogItem getBug(@PathVariable("id") String id) {
        return bugRepository.findOne(id);
    }

    @RequestMapping(value = "/bug", method = RequestMethod.GET)
    @ResponseBody
    List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @RequestMapping(value = "/bug/find", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity findBugs(@RequestBody List<String> bugs) {
        if (bugs == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bugRepository.findAll(bugs),HttpStatus.OK);
    }


    @RequestMapping(value = "/bug", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity addBacklogItem(@RequestBody Bug bug) {
        if (bug == null)
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bug.setId(new ObjectId().toString());
        bugRepository.save(bug);
        return new ResponseEntity<>(bug,HttpStatus.OK);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity updateTask(@PathVariable("id") String id, @RequestBody Bug bug) {
        if (bug == null || id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bugRepository.save(bug);
        return new ResponseEntity<>(bug,HttpStatus.OK);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity deleteBug(@PathVariable("id") String id) {
        if (id.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        bugRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}
