package net.codesrhereaman.jounalApp.services;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    //dependency injection
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(List.of("USER"));
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void saveNewAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(Arrays.asList("ADMIN","USER"));
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean saveNewUserForTestOnly(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(List.of("USER"));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
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
