package com.example.conference.advice;

import com.example.conference.teams.Team;
import com.example.conference.typeEditor.TeamTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class TeamBindingAdvice {

    private final TeamTypeEditor teamTypeEditor;

    public TeamBindingAdvice(TeamTypeEditor teamTypeEditor) {
        this.teamTypeEditor = teamTypeEditor;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Team.class,teamTypeEditor);
    }
}
