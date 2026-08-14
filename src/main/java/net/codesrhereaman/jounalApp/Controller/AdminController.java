package net.codesrhereaman.jounalApp.Controller;

import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalApp.JournalEntry.AdminCreateRequest;
import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userService.seeAllUsers();
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin-create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminCreateRequest user){
        try{
            User admin = new User();
            admin.setPassword(user.getPassword());
            admin.setUserName(user.getUserName());
            userService.saveNewAdmin(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
