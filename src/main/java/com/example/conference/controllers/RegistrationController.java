/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers;


import com.example.conference.forms.RegistrationForm;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    public RegistrationController(UserRepository UserRepo, PasswordEncoder passwordEncoder) {
        this.UserRepo = UserRepo;
        this.PasswordEncoder = passwordEncoder;
    }
    
    private UserRepository UserRepo;
    
    private PasswordEncoder PasswordEncoder;
   
    @ModelAttribute("registrationForm")
    public RegistrationForm form(){
        return new RegistrationForm();
    }
    
    @GetMapping()
    public String registrationPage() {
        return "registration";
    }
    
    @PostMapping()
    public String processRegistration( @Valid @ModelAttribute RegistrationForm registrationForm, BindingResult errors,Model model){
        if(errors.hasErrors()) return "registration";
        else
        if(UserRepo.findByUsername(registrationForm.getUsername()).isPresent()){
            model.addAttribute("ExistingUsernameError","Такой адрес уже зарегистрирован");
            return "registration";
        }
        else
        if(UserRepo.findByEmail(registrationForm.getEmail()).isPresent()){
            model.addAttribute("ExistingEmailError","Такой адрес уже зарегистрирован");
            return "registration";
        }
        else{
        User newUser = registrationForm.toUser(PasswordEncoder.encode(registrationForm.getPassword()));
        newUser.setRoles(Arrays.asList(new Role(0L,"ROLE_USER")));
        UserRepo.save(newUser);
        return "redirect:/login";   
        }
    }
}
