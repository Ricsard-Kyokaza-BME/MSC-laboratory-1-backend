package hu.bme.msc.agiletool.backlog_Item;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.repository.BacklogItemRepository;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BacklogItemController {
    @Autowired
    private BacklogItemRepository backlogItemRepository;

    @RequestMapping(value = "/backlog-item/{id}", method = RequestMethod.GET)
    @ResponseBody
    BacklogItem getBacklogItem(@PathVariable("id") String id) {
        System.out.println("id: " + id);
        return new Bug();
    }


    @RequestMapping(value = "/backlog-item", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity addBacklogItem(@RequestBody BacklogItem backlogItem) {
        if (backlogItem == null)
        {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        backlogItemRepository.save(backlogItem);
        return new ResponseEntity<BacklogItem>(backlogItem,HttpStatus.OK);
    }
}
