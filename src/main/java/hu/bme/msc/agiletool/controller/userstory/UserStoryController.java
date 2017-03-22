package hu.bme.msc.agiletool.controller.userstory;

import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserStoryController {

    @Autowired
    private UserStoryRepository userStoryRepository;

//    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    BacklogItem getUserStory(@PathVariable("id") String id) {
//        return userStoryRepository.findOne(id);
//    }
//
//    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    List<UserStory> getAllUserStory() {
//        return userStoryRepository.findAll();
//    }
//
//    @RequestMapping(value = "/userstory/find", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyAuthority('PO','USER')")
//    @ResponseBody
//    ResponseEntity findUserStories(@RequestBody List<String> stories) {
//        if (stories == null){
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
////        userStoryRepository.findAll(stories);
//        return new ResponseEntity<>(userStoryRepository.findAll(stories),HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/userstory", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyAuthority('PO')")
//    @ResponseBody
//    ResponseEntity addUserStory(@RequestBody UserStory userStory) {
//        if (userStory == null){
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        userStory.setId(new ObjectId().toString());
//        userStoryRepository.save(userStory);
//        return new ResponseEntity<>(userStory,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.PUT)
//    @PreAuthorize("hasAnyAuthority('PO')")
//    @ResponseBody
//    ResponseEntity updateUserStory(@PathVariable("id") String id, @RequestBody UserStory userStory) {
//        if (userStory == null || id.isEmpty())
//        {
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        userStoryRepository.save(userStory);
//        return new ResponseEntity<>(userStory,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('PO')")
//    @ResponseBody
//    ResponseEntity deleteUserStory(@PathVariable("id") String id) {
//        if (id.isEmpty())
//        {
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//        userStoryRepository.delete(id);
//        return new ResponseEntity<>(id,HttpStatus.OK);
//    }
}
