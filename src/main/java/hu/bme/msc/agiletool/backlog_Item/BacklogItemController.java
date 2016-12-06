package hu.bme.msc.agiletool.backlog_Item;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.repository.BugRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BacklogItemController {
    @Autowired
    private BugRepository bugRepository;

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
    @ResponseBody
    BacklogItem getBacklogItem(@PathVariable("id") String id) {
        System.out.println("id: " + id);
        return new Bug();
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
        return new ResponseEntity<Bug>(bug,HttpStatus.OK);
    }


}
