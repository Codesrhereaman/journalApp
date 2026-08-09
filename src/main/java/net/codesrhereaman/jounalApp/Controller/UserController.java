package net.codesrhereaman.jounalApp.Controller;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.UserRepository;
import net.codesrhereaman.jounalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//this is POJO(plain old java object)
@RestController
@RequestMapping("/user")   //gives a path to a class
public class UserController {

    @GetMapping
    public String ok(){
        return "alright";
    }

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> modifyUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User old = userService.findByUserName(name);
        old.setUserName( user.getUserName() );
        old.setPassword(user.getPassword());
        userService.saveNewUser(old);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/id")  //myid is a pth variable
//    public ResponseEntity<?> getUserById(@PathVariable ObjectId userid){
//        Optional<User> userById = userService.getUserById(userid);
//        if(userById.isPresent()){
//            return new ResponseEntity<>(userById.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping  //myid is a pth variable
    public ResponseEntity<?> deleteUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.deleteByUserName(name);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
