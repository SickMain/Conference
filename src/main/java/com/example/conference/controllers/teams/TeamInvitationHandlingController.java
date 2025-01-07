package com.example.conference.controllers.teams;

import com.example.conference.exceptions.UserInvitationException;
import com.example.conference.service.TeamsService;
import com.example.conference.teams.Team;
import com.example.conference.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/teams")
public class TeamInvitationHandlingController {

    private final TeamsService teamService;

    public TeamInvitationHandlingController(TeamsService teamsService) {
        this.teamService = teamsService;
    }

    @PostMapping("/invite/to/{teamId:\\d+}/{username}")
    @ResponseBody()
    public String inviteUserToTeamFormProceed(@PathVariable Long teamId, @PathVariable String username) {
        Team team = teamService.findTeamByIdOrThrown(teamId);
        User user = teamService.findUserByUsernameOrThrown(username);
        teamService.inviteUser(team, user);
        return "Пользователь приглашен";
    }

    @PostMapping("/invite/accept/{code}")
    public String acceptInviteFormProceed(@PathVariable String code, @AuthenticationPrincipal User logedUser) {
        User user = teamService.findUserByUsernameOrThrown(logedUser.getUsername());
        teamService.acceptInvite(code);

        return "redirect:/teams/with/" + user.getUsername();
    }

    @PostMapping("/invite/decline/{code}")
    public String declineInviteFormProceed(@PathVariable String code, @AuthenticationPrincipal User logedUser) {
        User user = teamService.findUserByUsernameOrThrown(logedUser.getUsername());
        teamService.declineInvite(code);

        return "redirect:/teams/with/" + user.getUsername();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
