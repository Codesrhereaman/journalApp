package net.codesrhereaman.jounalApp.services;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //there are annotation like beforeEach beforeAll AfterEach AfterAll : to set up something for tests before or after the tests as name suggest

    @Disabled
    @Test
    public void justTest(){
        assertEquals(2,1+1);
    }


    @ParameterizedTest    //to create
    @ValueSource(strings = { "aman"})
    @Disabled
    public void deleteByUserNameTest(String userName){
        assertNotNull(userRepository.deleteByUserName(userName));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void saveUserTest(User user){
        assertTrue(userService.saveNewUserForTestOnly(user));
    }
}
