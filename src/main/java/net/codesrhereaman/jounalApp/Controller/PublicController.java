package net.codesrhereaman.jounalApp.Controller;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    //health-check is important to check if the server is running and api will response
    @GetMapping("/health-check")
    public String healthcheck(){
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin-create")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        try{
            userService.saveNewAdmin(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
