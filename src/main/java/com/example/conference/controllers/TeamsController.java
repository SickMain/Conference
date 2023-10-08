/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers;

import com.example.conference.forms.NewTeamForm;
import com.example.conference.model.Team;
import com.example.conference.model.UserInTeam;
import com.example.conference.repository.TeamsRepository;
import com.example.conference.repository.UserRepository;
import com.example.conference.service.TeamsService;
import com.example.conference.user.User;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author user
 */
@Controller
@CrossOrigin
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
    @PostMapping("/invite/to/{id}/{username}")
    @ResponseBody()
    public String inviteUserToTeam(@PathVariable Long id,@PathVariable String username) throws Exception {
        Team team = teamsRepo.findById(id).get();
        if (userRepo.findByUsername(username).isEmpty()) throw new Exception("Пользователь не найден");
        User user = userRepo.findByUsername(username).get();
        Set<User> inTeam = team.getUserInTeam().stream().map(x -> x.getUser()).collect(Collectors.toSet());
        if (inTeam.contains(user)) throw new Exception("Пользователь уже в команде");
        teamService.inviteUser(team,user);
        return "Пользователь приглашен";
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
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}