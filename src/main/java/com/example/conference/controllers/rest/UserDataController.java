/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.rest;

import com.example.conference.repository.user.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/data/users")
public class UserDataController {


    private final UserRepository userRepo;

    public UserDataController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @CrossOrigin
    @GetMapping("/handles")
    @ResponseBody
    public List<String> getHandles(@RequestParam("tagName") String username) {
        List<User> userList = userRepo.findAll();

        return userList.stream().filter(x -> !x.getRoles()
                        .contains(new Role(1L, "ROLE_ADMIN")))
                .map(user -> user.getUsername())
                .filter(s -> s.toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
    }

}
