package net.codesrhereaman.jounalapp.config;

import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalapp.filter.JwtFilter;
import net.codesrhereaman.jounalapp.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {

    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            http
                    .csrf(csrf -> csrf.disable())  //it helps to protect cross-site request forgery

                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  //in case of token, it os enabled(default)
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/journal/**", "/user/**").authenticated()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().permitAll())
            ;
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

}
