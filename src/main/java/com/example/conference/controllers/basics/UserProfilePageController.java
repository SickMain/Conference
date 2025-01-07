/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.basics;

import com.example.conference.exceptions.UserProfileNotFoundException;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.service.UserService;
import com.example.conference.user.Role;
import com.example.conference.user.User;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/profile/{username}")
public class UserProfilePageController {


    private final UserService userService;

    public UserProfilePageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userProfilePage(Model model, @PathVariable String username, @AuthenticationPrincipal User currentUser) {
        return userService.findUserByUsername(username).map(user -> {
            if (!user.getRoles().contains(new Role(2L, "ROLE_ADMIN"))) {
                model.addAttribute("user", user);
                if (user.equals(currentUser)) {
                    model.addAttribute("loged", true);
                }
            }
            return "profile";
        }).orElseThrow(() -> new UserProfileNotFoundException());
    }

    @PostMapping("/changeProfileImg")
    public String changeProfileImgFormProceed(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file) {
        userService.changeProfileImg(user.getUsername(), file);
        return "redirect:/profile/" + user.getUsername();
    }


    @PostMapping("/dropProfileImg")
    public String dropProfileImgFormProceed(@AuthenticationPrincipal User user) {
        userService.dropProfileImg(user.getUsername());
        return "redirect:/profile/" + user.getUsername();
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public String handleUserProfileNotFoundException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
