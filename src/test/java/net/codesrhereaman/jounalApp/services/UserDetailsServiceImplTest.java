package net.codesrhereaman.jounalApp.services;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static org.mockito.Mockito.when;


//PASSED BUT STILL IT IS USING DB AS @SPRINGBOOTTEST IS GIVEN
//@SpringBootTest
//public class UserDetailsServiceImplTest {
//
//   @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @MockitoBean
//    private UserRepository userRepository;
//
//    //we are creating mock of user repository
//
//    @Test
//    public void loadUserByUsernameTest(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("xyz").password("xyz").userRoles(Arrays.asList("USER")).build());
//        UserDetails aman = userDetailsService.loadUserByUsername("aman");
//        Assertions.assertNotNull(aman);
//    }
//}
//
//

@ExtendWith(MockitoExtension.class)
@Disabled
public class UserDetailsServiceImplTest {

   @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    //we are creating mock of user repository


    //gives exception if we had not initialise the mock using ExtendsWith.... or openMocks method
    @Test
    public void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("xyz").password("xyz").userRoles(Arrays.asList("USER")).build());
        UserDetails aman = userDetailsService.loadUserByUsername("aman");
        Assertions.assertNotNull(aman);
    }
}
