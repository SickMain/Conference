/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conferenceisu.configuration;


import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 *
 * @author user
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
    
        http
                .csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                )
                .logout(form -> form
                .logoutSuccessUrl("/")
                .permitAll()
                )
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/conferences").hasRole("USER")
                .requestMatchers("/", "/**").permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService webUserDetailsService(UserRepository userRepo) {
        return username -> {
            Optional<User> user = userRepo.findByEmail(username);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }
}
