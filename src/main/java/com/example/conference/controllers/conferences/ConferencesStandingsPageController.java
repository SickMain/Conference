package com.example.conference.controllers.conferences;

import com.example.conference.contests.Conference;
import com.example.conference.exceptions.ConferenceNotFoundException;
import com.example.conference.service.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contests/standings")
public class ConferencesStandingsPageController {

    private final ConferenceService conferenceService;

    public ConferencesStandingsPageController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/{conferenceId:\\d+}")
    public String standingsPage(Model model, @PathVariable Long conferenceId){
        Conference conference = conferenceService.findConferenceByIdOrThrown(conferenceId);
        model.addAttribute(conference);
        model.addAttribute("standings",conferenceService.sortedTeamRecordsBySummaryMark(conferenceId));
        return "conferencesStandings";
    }
}
