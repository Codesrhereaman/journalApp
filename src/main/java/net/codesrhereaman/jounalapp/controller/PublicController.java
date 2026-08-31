package net.codesrhereaman.jounalapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.journalentry.User;
import net.codesrhereaman.jounalapp.journalentry.dto.UserRequest;
import net.codesrhereaman.jounalapp.services.UserDetailsServiceImpl;
import net.codesrhereaman.jounalapp.services.UserService;
import net.codesrhereaman.jounalapp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@Slf4j
public class PublicController {


    private final UserService userService;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    //health-check is important to check if the server is running and api will response
    @GetMapping("/health-check")
    public String healthcheck(){
        return "Ok";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request){
        try{
            userService.saveNewUser(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.userName(),request.password()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.userName());
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch (Exception e){
            log.error("Invalid username or password");
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
