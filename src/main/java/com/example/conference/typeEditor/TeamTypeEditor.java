package com.example.conference.typeEditor;

import com.example.conference.service.TeamsService;
import com.example.conference.teams.Team;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TeamTypeEditor extends PropertyEditorSupport {

    private final TeamsService teamsService;

    public TeamTypeEditor(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Team team = teamsService.findTeamByIdOrThrown(Long.valueOf(text));
        setValue(team);
    }
}