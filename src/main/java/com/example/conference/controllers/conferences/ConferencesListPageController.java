/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.conferences;

import com.example.conference.contests.Conference;
import com.example.conference.front.model.Pager;
import com.example.conference.service.ConferenceService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/contests")
public class ConferencesListPageController {

    private final ConferenceService conferenceService;

    public ConferencesListPageController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(LocalDateTime.now().getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute());
    }

    @GetMapping()
    public String conferencesListPage(@RequestParam(defaultValue = "0") Integer currentPage, Model model) {
        int totalPages = conferenceService.getTotalPages();
        List<Conference> conferencePage = conferenceService.getConferencePage(currentPage);
        model.addAttribute("conferencePage", conferencePage);
        model.addAttribute("pager", new Pager(totalPages, currentPage));
        model.addAttribute("currentPage", currentPage);
        return "conferences";
    }


}
