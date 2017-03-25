package hu.bme.msc.agiletool.controller.user;

import hu.bme.msc.agiletool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
//    @ResponseBody
//    ResponseEntity getUsers(@RequestBody UserSearchRequest searchRequest) {
//        if(searchRequest == null){
//            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//
//        List<User> retVal = new ArrayList<>();
//        for (User iter: userRepository.findAll()) {
//            if(iter.getFullName().toUpperCase().contains(searchRequest.getKeyword().toUpperCase())){
//                retVal.add(iter);
//            }
//        }
//        return new ResponseEntity<>(retVal,HttpStatus.OK);
//    }

    @RequestMapping(value = "/api/user/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findUsersByIds(@RequestBody List<String> users) {
        if (users == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepository.findAll(users),HttpStatus.OK);
    }
}
