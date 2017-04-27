package hu.bme.msc.agiletool.controller.bug;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BugController implements PredefineBaseController {

    @Autowired
    private BugRepository bugRepository;

    @RequestMapping(value = "/bug/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findBugs(@RequestBody List<String> bugs) {
        if (bugs == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bugRepository.findAll(bugs), HttpStatus.OK);
    }
}
