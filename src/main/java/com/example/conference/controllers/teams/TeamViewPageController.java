package com.example.conference.controllers.teams;

import com.example.conference.access.TeamAccessVerifier;
import com.example.conference.service.TeamsService;
import com.example.conference.teams.Team;
import com.example.conference.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teams")
public class TeamViewPageController {

    private final TeamsService teamService;

    private final TeamAccessVerifier teamAccessVerifier;

    public TeamViewPageController(TeamsService teamService,TeamAccessVerifier teamAccessVerifier) {
        this.teamService = teamService;
        this.teamAccessVerifier = teamAccessVerifier;
    }

    @GetMapping("/view/{teamId:\\d+}")
    public String viewTeamPage(Model model, @PathVariable Long teamId, @AuthenticationPrincipal User user) {
        Team team = teamService.findTeamByIdOrThrown(teamId);
        model.addAttribute("team", team);
        if (teamAccessVerifier.isTeamLeader(user, teamId)) {
            model.addAttribute("canEditTeam", true);
        }
        else if (teamAccessVerifier.isTeamMember(user, teamId)) {
            model.addAttribute("canLeaveTeam", true);
        }
        return "viewTeam";
    }
}
