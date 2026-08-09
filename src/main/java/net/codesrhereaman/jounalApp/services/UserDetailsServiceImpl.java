package net.codesrhereaman.jounalApp.services;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
       if(user!=null){
           return org.springframework.security.core.userdetails.User
                   .withUsername(user.getUserName())
                   .password(user.getPassword())
                   .roles(user.getUserRoles().toArray(new String[0]))
                   .build();
       }
       throw new UsernameNotFoundException("user name not found : " + username );
    }
}
