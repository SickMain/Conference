package com.example.conference.controllers.teams;

import com.example.conference.front.forms.NewTeamForm;
import com.example.conference.service.TeamsService;
import com.example.conference.user.User;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teams/new")
public class TeamAddPageController {

    private final TeamsService teamService;

    public TeamAddPageController(TeamsService teamsService) {
        this.teamService = teamsService;
    }

    @GetMapping
    public String createNewTeamPage(Model model) {
        model.addAttribute("newTeamForm", new NewTeamForm());
        return "newTeamForm";
    }

    @PostMapping
    public String createNewTeamFormProceed(@Valid @ModelAttribute("newTeamForm") NewTeamForm teamForm, Errors errors, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) return "newTeamForm";

        teamService.saveTeam(teamForm.toTeam(), user);
        return "redirect:/teams/with/" + user.getUsername();

    }
}
