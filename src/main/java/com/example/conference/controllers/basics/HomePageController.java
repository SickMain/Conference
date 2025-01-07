/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.conference.controllers.basics;

import com.example.conference.contests.Conference;
import com.example.conference.service.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class HomePageController {

    private final ConferenceService conferenceService;

    public HomePageController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime currentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(LocalDateTime.now().getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute());
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Conference> recentConferences = conferenceService.getRecentConference();
        model.addAttribute("recentConferences", recentConferences);
        return "home";
    }

}
