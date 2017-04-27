package hu.bme.msc.agiletool.controller.userstory;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStoryController implements PredefineBaseController {

    @Autowired
    private UserStoryRepository userStoryRepository;

    @RequestMapping(value = "/userstory/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findUserStories(@RequestBody List<String> stories) {
        if (stories == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStoryRepository.findAll(stories), HttpStatus.OK);
    }
}
