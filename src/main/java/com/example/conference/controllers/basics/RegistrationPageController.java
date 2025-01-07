/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.basics;


import com.example.conference.front.forms.RegistrationForm;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.service.UserService;
import com.example.conference.user.Role;
import com.example.conference.user.User;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author user
 */
@Controller
@RequestMapping("/registration")
public class RegistrationPageController {

    public RegistrationPageController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.PasswordEncoder = passwordEncoder;
    }

    private final UserService userService;

    private final PasswordEncoder PasswordEncoder;

    @ModelAttribute("registrationForm")
    public RegistrationForm form() {
        return new RegistrationForm();
    }

    @GetMapping()
    public String registrationPage() {
        return "registration";
    }

    @PostMapping()
    public String registrationFormProceed(@Valid @ModelAttribute RegistrationForm registrationForm, BindingResult errors) {
        if (errors.hasErrors()) return "registration";

        User newUser = registrationForm.toUser(PasswordEncoder.encode(registrationForm.getPassword()));
        newUser.setRoles(List.of(new Role(1L, "ROLE_USER")));
        userService.saveUser(newUser);
        return "redirect:/login";

    }
}
