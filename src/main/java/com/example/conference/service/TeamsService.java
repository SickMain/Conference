/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conferenceisu.service;

import com.example.conferenceisu.forms.RegistrationForm;
import com.example.conferenceisu.model.Team;
import com.example.conferenceisu.model.UserInTeam;
import com.example.conferenceisu.repository.TeamsRepository;
import com.example.conferenceisu.repository.UserInTeamRepository;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.Role;
import com.example.conferenceisu.user.User;
import java.util.List;
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
        return  teamsRepo.findDistinctByUserInTeam_UserId( userRepo.findByUsername(username).get().getId());
    }
    
    public Team saveTeam(Team team,User user){
        team.setLeader(user);
        Team createdTeam = teamsRepo.save(team);
        UserInTeam UIT = new UserInTeam(user,createdTeam);
        userInTeamRepo.save(UIT);
        
        return team;
    }
    
}
