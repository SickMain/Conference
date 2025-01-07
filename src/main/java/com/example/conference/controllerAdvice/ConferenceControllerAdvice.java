package com.example.conference.controllerAdvice;

import com.example.conference.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice()
public class ConferenceControllerAdvice {

    @ExceptionHandler(TeamEditingAccessDeniedException.class)
    public String TeamEditingAccessDenied(TeamEditingAccessDeniedException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(TeamNotChosenException.class)
    public String handleTeamNotChosenException(TeamNotChosenException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/contests/view/" + e.getConferenceId();
    }

    @ExceptionHandler(TeamMembersAlreadyRegisteredException.class)
    public String TeamMembersAlreadyRegisteredException(TeamMembersAlreadyRegisteredException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/contests/view/" + e.getConferenceId();
    }

    @ExceptionHandler(ConferenceNotFoundException.class)
    public String handleConferenceNotFoundException(ConferenceNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/contests";
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public String handleTeamNotFoundException(TeamNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public String handleUserProfileNotFoundException(UserProfileNotFoundException e,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alertMessage",e.getMessage());
        return "redirect:/";
    }
}
