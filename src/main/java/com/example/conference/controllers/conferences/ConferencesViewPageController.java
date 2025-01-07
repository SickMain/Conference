package com.example.conference.controllers.conferences;

import com.example.conference.contests.Conference;
import com.example.conference.exceptions.TeamMembersAlreadyRegisteredException;
import com.example.conference.exceptions.TeamNotChosenException;
import com.example.conference.exceptions.ConferenceNotFoundException;
import com.example.conference.exceptions.TeamNotFoundException;
import com.example.conference.service.ConferenceService;
import com.example.conference.service.TeamsService;
import com.example.conference.teams.Team;
import com.example.conference.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contests/view")
public class ConferencesViewPageController {

    private final ConferenceService conferenceService;

    private final TeamsService teamsService;

    public ConferencesViewPageController(ConferenceService conferenceService, TeamsService teamsService) {
        this.conferenceService = conferenceService;
        this.teamsService = teamsService;
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(LocalDateTime.now().getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute());
    }

    @GetMapping("/{conferenceId:\\d+}")
    public String viewConferencePage(@PathVariable Long conferenceId, Model model, @AuthenticationPrincipal User user) {
        Conference conference = conferenceService.findConferenceByIdOrThrown(conferenceId);
        model.addAttribute("conference", conference);
        if(user != null) {
            List<Team> teamsByUsernameAndLeaderStatus = teamsService.findTeamsByUsernameAndLeaderStatus(user.getUsername());
            model.addAttribute("currentUserTeamList", teamsByUsernameAndLeaderStatus);
        }
        return "conference";
    }

    @PostMapping("/registerTeam")
    public String teamRegistrationFormProceed(@RequestParam("conferenceId") Conference conference, @RequestParam(name = "chosenTeamId") Optional<Long> teamId) {
        teamId.map(id -> {
            Team team = teamsService.findTeamByIdOrThrown(id);
            conferenceService.registerTeam(conference, team);
            return true;
        }).orElseThrow(() -> new TeamNotChosenException(conference.getId()));
        return "redirect:/contests/standings/" + conference.getId();
    }


}
