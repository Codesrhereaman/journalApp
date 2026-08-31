package net.codesrhereaman.jounalapp.services;

import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.constants.UserRoles;
import net.codesrhereaman.jounalapp.journalentry.User;
import net.codesrhereaman.jounalapp.journalentry.dto.UserRequest;
import net.codesrhereaman.jounalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    //dependency injection
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(UserRequest request) {
        try {
            User user = new User();
            user.setUserName(request.userName());
            user.setEmail(request.email());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(List.of(UserRoles.roles.USER.name()));
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void saveNewAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(Arrays.asList(UserRoles.roles.USER.name(),UserRoles.roles.ADMIN.name()));
            userRepository.save(user);
        }catch (Exception e){
            log.error("Cannot find user");
        }
    }

    public boolean saveExistingUser(User user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            log.error("Cannot find user");
        }
        return false;
    }

    public List<User> seeAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    public boolean deleteUserById(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteAllUsers() {
        userRepository.deleteAll();
        return true;
    }

}
