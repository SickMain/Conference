/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers;

import com.example.conference.repository.UserRepository;
import com.example.conference.user.User;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/profile/{username}")
public class ProfileController {
    
    @Value("${upload.path}")
    private String uploadPath;
    
    private UserRepository userRepo;

    public ProfileController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
   
    
    @GetMapping
    public String profilePage(Model model,@PathVariable String username, @AuthenticationPrincipal User loged) throws Exception {
        Optional<User> user = userRepo.findByUsername(username);
       
        if (user.isPresent()){
        model.addAttribute("user", user.get());
        
        if(user.get().equals(loged)){
            model.addAttribute("loged",true);
        }
        return "profile";
        }
        throw new Exception("Пользователь не найден");
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }
    @PostMapping("/changeProfileImg")
    public String changeProfileImg(@AuthenticationPrincipal User loged,@RequestParam("file") MultipartFile file) throws IOException{
       if (!file.isEmpty()){
           File uploadDir = new File(uploadPath);
           
           if(!uploadDir.exists()){
               uploadDir.mkdir();
           }
           
           String resultFileName = loged.getUsername() + "." + file.getOriginalFilename();
           
           file.transferTo(new File(uploadPath+"/"+resultFileName));
           
           File delete = new File(uploadPath+"/"+loged.getProfileImg());
           delete.delete();
           
      
           
           loged.setProfileImg(resultFileName);
           
           userRepo.save(loged);
       }
       return "redirect:/profile/" + loged.getUsername();
    }
    
}
