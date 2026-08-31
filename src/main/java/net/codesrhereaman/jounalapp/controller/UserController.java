package net.codesrhereaman.jounalapp.controller;

import net.codesrhereaman.jounalapp.journalentry.dto.GreetingResponse;
import net.codesrhereaman.jounalapp.journalentry.User;
import net.codesrhereaman.jounalapp.journalentry.WeatherResponse;
import net.codesrhereaman.jounalapp.journalentry.dto.UserRequest;
import net.codesrhereaman.jounalapp.repository.UserRepository;
import net.codesrhereaman.jounalapp.services.UserService;
import net.codesrhereaman.jounalapp.services.WeatherService;
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

//    @GetMapping
//    public String ok(){
//        return "alright";
//    }

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> modifyUser(@RequestBody UserRequest user){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        User old = userService.findByUserName(name);

        if (user.userName() != null) {
            old.setUserName(user.userName());
        }

        if (user.password() != null) {
            old.setPassword(user.password());
        }

        if (user.email() != null) {
            old.setEmail(user.email());
        }

        if (user.sentimentalAnalysis() != null) {
            old.setSentimentalAnalysis(user.sentimentalAnalysis());
        }


        userService.saveExistingUser(old);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> gretting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.weatherResponse("Noida");
        if(weather!=null){
            GreetingResponse greeting = new GreetingResponse(weather,"Hi "+ authentication.getName());
            return  ResponseEntity.ok(greeting);
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping  //myid is a pth variable
    public ResponseEntity<?> deleteUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.deleteByUserName(name);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }




}
