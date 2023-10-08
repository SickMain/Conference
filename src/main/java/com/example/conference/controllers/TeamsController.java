/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conferenceisu.controllers;

import com.example.conferenceisu.forms.NewTeamForm;
import com.example.conferenceisu.model.Team;
import com.example.conferenceisu.repository.TeamsRepository;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.service.TeamsService;
import com.example.conferenceisu.user.User;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author user
 */
@Controller
@RequestMapping("/teams")
public class TeamsController {

    private final UserRepository userRepo;
    private final TeamsRepository teamsRepo;
    
    private final TeamsService teamService;

    public TeamsController(UserRepository userRepo, TeamsService teamsService,TeamsRepository teamsRepo) {
        this.userRepo = userRepo;
        this.teamService = teamsService;
        this.teamsRepo = teamsRepo;
    }

    @GetMapping("/with/{username}")
    public String getUsersTeams(@PathVariable String username, Model model, @AuthenticationPrincipal User loged) {

        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());

            if (user.get().equals(loged)) {
                model.addAttribute("loged", true);
            }
                model.addAttribute("teamsList",teamService.findTeamsByUsername(username));
            return "usersTeams";
        }
        else return "redirect:/";

    }
    @GetMapping("/edit/{id}")
    public String editTeamForm(Model model,@PathVariable Long id){
        Optional<Team> team = teamsRepo.findById(id);
        if (team.isPresent()){
            model.addAttribute("team",team.get());
            return "team";
        }
        else{
            return "redirect:/";
        }

    }
    @GetMapping("/new")
    public String createNewTeamForm(Model model){
        model.addAttribute("newTeamForm",new NewTeamForm());
        return "newTeamForm";
    }
    
    @PostMapping("/new") 
    public String createNewTeam(@Valid @ModelAttribute("newTeamForm") NewTeamForm teamForm,Errors errors,@AuthenticationPrincipal User user){
       if(errors.hasErrors()){
           return "newTeamForm";
       }
       else{
           teamService.saveTeam(teamForm.toTeam(), user);
           return "redirect:/teams/with/" + user.getUsername();
       }
    }
}