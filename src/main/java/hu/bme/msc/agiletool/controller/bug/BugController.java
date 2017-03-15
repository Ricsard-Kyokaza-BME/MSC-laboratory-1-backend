package hu.bme.msc.agiletool.controller.bug;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.repository.BugRepository;
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
public class BugController {

    @Autowired
    private BugRepository bugRepository;

//    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    BacklogItem getBug(@PathVariable("id") String id) {
//        return bugRepository.findOne(id);
//    }
//
//    @RequestMapping(value = "/bug", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    public List<Bug> getAllBugs() {
//        System.out.println(bugRepository);
//        System.out.println(bugRepository.findAll());
//        return bugRepository.findAll();
//    }
//
//    @RequestMapping(value = "/bug/find", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity findBugs(@RequestBody List<String> bugs) {
//        if (bugs == null){
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(bugRepository.findAll(bugs),HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/bug", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity addBug(@RequestBody Bug bug) {
//        if (bug == null)
//        {
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        bug.setId(new ObjectId().toString());
//        bugRepository.save(bug);
//        return new ResponseEntity<>(bug,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/bug/{id}", method = RequestMethod.PUT)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity updateBug(@PathVariable("id") String id, @RequestBody Bug bug) {
//        if (bug == null || id.isEmpty())
//        {
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        bugRepository.save(bug);
//        return new ResponseEntity<>(bug,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/bug/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity deleteBug(@PathVariable("id") String id) {
//        if (id.isEmpty())
//        {
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        bugRepository.delete(id);
//        return new ResponseEntity<>(id,HttpStatus.OK);
//    }
//
//    public BugRepository getBugRepository() {
//        return bugRepository;
//    }
//
//    public void setBugRepository(BugRepository bugRepository) {
//        this.bugRepository = bugRepository;
//    }
//
//    public BugController() {}
}
