package hu.bme.msc.agiletool.controller.task;


import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController implements PredefineBaseController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/task/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findTasks(@RequestBody List<String> tasks) {
        if (tasks == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskRepository.findAll(tasks), HttpStatus.OK);
    }
}
