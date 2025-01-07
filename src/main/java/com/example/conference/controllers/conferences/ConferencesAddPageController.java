package com.example.conference.controllers.conferences;

import com.example.conference.contests.Criteria;
import com.example.conference.front.forms.NewConferenceForm;
import com.example.conference.service.ConferenceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/contests/new")
public class ConferencesAddPageController {

    private final ConferenceService conferenceService;

    public ConferencesAddPageController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(LocalDateTime.now().getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute());
    }

    @GetMapping
    public String newConferencePage(@ModelAttribute("criteriaNum") int criteriaNum, Model model) {
        NewConferenceForm conferenceForm = new NewConferenceForm();
        for (int i = 0; i < criteriaNum; i++) conferenceForm.addCriteria(new Criteria());
        model.addAttribute("conferenceForm", conferenceForm);
        return "newConferenceForm";
    }

    @PostMapping("/proceed")
    public String newConferenceFormProceed(@Valid @ModelAttribute("conferenceForm") NewConferenceForm conferenceForm, BindingResult errors) {
        if (errors.hasErrors()) return "newConferenceForm";

        conferenceService.registerConference(conferenceForm.toConference());
        return "redirect:/contests";
    }
}
