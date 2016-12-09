package hu.bme.msc.agiletool.user_controller;

import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    User getOneUser(@PathVariable("id") String id) {
        return userRepository.findOne(id);
    }


    @RequestMapping(value = "/user/find", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity getUsers(@RequestBody List<String> users) {
        if(users == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepository.findAll(users),HttpStatus.OK);
    }


}
