package com.example.conference.controllers.conferences;

import com.example.conference.contests.CriteriaMark;
import com.example.conference.contests.TeamRecord;
import com.example.conference.contests.UserInTeamRecord;
import com.example.conference.front.forms.TeamMarkUpdateForm;
import com.example.conference.service.ConferenceService;
import com.example.conference.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/team/record")
public class ConferencesTeamRecordViewPageController {

    private final ConferenceService conferenceService;

    public ConferencesTeamRecordViewPageController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/view/{teamRecordId:\\d+}")
    public String conferencesTeamRecordViewPage(@PathVariable("teamRecordId") TeamRecord teamRecord, Model model){
        List<UserInTeamRecord> userInTeamRecords = conferenceService.teamRecordsUsers(teamRecord.getId());
        List<User> usersInfo = conferenceService.findAllUsersByIds(userInTeamRecords.stream().map(u->u.getUserId()).collect(Collectors.toList()));
        model.addAttribute("teamRecord",teamRecord);
        model.addAttribute("criteriaMarks",new TeamMarkUpdateForm(teamRecord.getCriteriaMarks().stream().toList(),teamRecord.getConference().getId()));
        model.addAttribute("teamUsersList",usersInfo);
        return "teamRecordView";
    }

    @PostMapping("/view/{teamRecordId:\\d+}")
    public String conferencesTeamRecordUpdateFormProceed(@ModelAttribute("criteriaMarks") TeamMarkUpdateForm criteriaMarks){
        conferenceService.saveAllCriteriaMarks(criteriaMarks.getCriteriaMarkSet());
        return "redirect:/contests/standings/"+criteriaMarks.getConferenceId();
    }

    @PostMapping("/disqualify/{teamRecordId:\\d+}")
    public String conferencesTeamRecordUpdateFormProceed(@PathVariable Long teamRecordId,@RequestParam("conferenceId") Long conferenceId){
        conferenceService.disqualifyTeam(teamRecordId);
        return "redirect:/contests/standings/"+conferenceId;
    }

}
