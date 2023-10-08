/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conferenceisu.controllers;

import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/data/users")
public class UserDataController {

    
    private UserRepository userRepo;

    public UserDataController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    @CrossOrigin
    @GetMapping("/handles")
    @ResponseBody
    public List<String> getHandles(@RequestParam("tagName") String username) {
       List <User> userList  = userRepo.findAll();
        
       return userList.stream().map( user-> user.getUsername() ).filter( s -> s.toLowerCase().contains(username.toLowerCase())).collect(Collectors.toList());
    }
    
}
