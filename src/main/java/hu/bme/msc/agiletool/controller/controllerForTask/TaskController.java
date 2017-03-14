package hu.bme.msc.agiletool.controller.controllerForTask;


import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Task;
import hu.bme.msc.agiletool.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

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
}
