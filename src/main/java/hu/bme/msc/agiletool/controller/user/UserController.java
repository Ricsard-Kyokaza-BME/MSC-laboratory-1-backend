package hu.bme.msc.agiletool.controller.user;

//@RestController
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
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
//}
