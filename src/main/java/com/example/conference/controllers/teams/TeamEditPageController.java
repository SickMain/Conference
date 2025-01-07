/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.teams;

import com.example.conference.exceptions.TeamNotFoundException;
import com.example.conference.service.TeamsService;
import com.example.conference.teams.Team;
import com.example.conference.user.User;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@CrossOrigin
@RequestMapping("/teams")
public class TeamEditPageController {

    private final TeamsService teamService;


    public TeamEditPageController(TeamsService teamsService) {
        this.teamService = teamsService;
    }

    @GetMapping("/edit/{teamId:\\d+}")
    @PreAuthorize("@teamAccessVerifier.isTeamLeader(authentication,#team)")
    public String editTeamPage(Model model, @PathVariable("teamId") Team team) {
        model.addAttribute("team", team);
        return "editTeam";
    }

    @PostMapping("/excludeUserFromTeam/{teamId:\\d+}/{username}")
    @PreAuthorize("@teamAccessVerifier.isTeamLeader(authentication,#team)")
    public String excludeUserFromTeamFormProceed(@PathVariable("teamId") Team team, @PathVariable String username) {
        teamService.deleteUserFromTeam(team, username);
        return "redirect:/teams/edit/" + team.getId();
    }

    @PostMapping("/leave/{teamId:\\d+}")
    public String leaveTeamFormProceed(@AuthenticationPrincipal User user, @PathVariable Long teamId) {
        Team team = teamService.findTeamByIdOrThrown(teamId);
        teamService.deleteUserFromTeam(team, user.getUsername());

        return "redirect:/teams/with/" + user.getUsername();
    }

    @PostMapping("/delete/{teamId:\\d+}")
    @PreAuthorize("@teamAccessVerifier.isTeamLeader(authentication,#team)")
    public String deleteTeam(@PathVariable("teamId") Team team) {
        teamService.deleteTeam(team);
        String leader = team.getLeader().getUsername();

        return "redirect:/teams/with/" + leader;
    }


}