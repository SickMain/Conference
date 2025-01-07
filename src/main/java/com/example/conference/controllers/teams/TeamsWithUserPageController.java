package com.example.conference.controllers.teams;

import com.example.conference.service.TeamsService;
import com.example.conference.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teams/with")
public class TeamsWithUserPageController {
    private final TeamsService teamService;

    public TeamsWithUserPageController(TeamsService teamsService) {
        this.teamService = teamsService;
    }

    @GetMapping("/{username}")
    public String usersTeamsPage(@PathVariable String username, Model model) {
        User user = teamService.findUserByUsernameOrThrown(username);
        model.addAttribute("user", user);
        model.addAttribute("teamsList", teamService.findTeamsByUsername(username));
        return "usersTeams";
    }
}
