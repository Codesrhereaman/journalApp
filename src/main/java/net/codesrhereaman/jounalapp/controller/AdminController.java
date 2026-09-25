package net.codesrhereaman.jounalapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.cache.AppCache;
import net.codesrhereaman.jounalapp.journalentry.dto.AdminCreateRequest;
import net.codesrhereaman.jounalapp.journalentry.User;
import net.codesrhereaman.jounalapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@Tag(name = "Admin API's")
public class AdminController {


    private final UserService userService;

    private final AppCache appCache;

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

    @GetMapping("clear-app-cache")
    public void clearAppCache() {
        try {
            appCache.init();
            log.warn("app cache cleared");
        }catch (Exception e){
            log.error("can't clear cache");
        }
    }

}
