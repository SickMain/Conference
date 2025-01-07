/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.configuration;


import ch.qos.logback.core.net.ssl.SSLContextFactoryBean;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.user.User;

import java.net.http.HttpClient;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.client.RestTemplate;



@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
            http
                .csrf(csrf -> csrf.csrfTokenRepository(
                        new HttpSessionCsrfTokenRepository()
                        )
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
                .requestMatchers("/contests/new").hasRole("ADMIN")
                .requestMatchers("/", "/**").permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService webUserDetailsService(UserRepository userRepo) {
        return email -> {
            Optional<User> user = userRepo.findByEmail(email);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UsernameNotFoundException("User ‘" + email + "’ not found");
        };
    }


}
