/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.service;

import com.example.conference.forms.RegistrationForm;
import com.example.conference.model.Team;
import com.example.conference.model.UserInTeam;
import com.example.conference.repository.TeamsRepository;
import com.example.conference.repository.UserInTeamRepository;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class TeamsService {
        
    private final UserRepository userRepo;
    
    private final TeamsRepository teamsRepo;

    private final UserInTeamRepository userInTeamRepo;

    public TeamsService(UserRepository userRepo, TeamsRepository teamsRepo, UserInTeamRepository userInTeamRepo) {
        this.userRepo = userRepo;
        this.teamsRepo = teamsRepo;
        this.userInTeamRepo = userInTeamRepo;
    }
    
    public List<Team> findTeamsByUsername(String username){

        User user = userRepo.findByUsername(username).get();
        List<Team> teams = teamsRepo.findDistinctByUserInTeam_UserId(user.getId());
        List<Team> activeTeams = new ArrayList<>();
        for (Team team: teams){
            Set<UserInTeam> st = team.getUserInTeam();
            for (UserInTeam userInTeam:st){
                if (userInTeam.getUser().equals(user) && userInTeam.isActive())activeTeams.add(team);
            }
        }
        return activeTeams;
    }
    
    public Team saveTeam(Team team,User user){
        team.setLeader(user);
        Team createdTeam = teamsRepo.save(team);
        UserInTeam UIT = new UserInTeam(user,createdTeam);
        userInTeamRepo.save(UIT);
        
        return team;
    }

    public void inviteUser(Team team,User user){
        UserInTeam UIT = new UserInTeam(user,team);
        UIT.setIsActive(false);
        userInTeamRepo.save(UIT);
    }
    
}
