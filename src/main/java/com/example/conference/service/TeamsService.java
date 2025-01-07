/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.service;

import com.example.conference.exceptions.TeamNotFoundException;
import com.example.conference.exceptions.UserProfileNotFoundException;
import com.example.conference.teams.Team;
import com.example.conference.teams.UserInTeam;
import com.example.conference.repository.teams.TeamsRepository;
import com.example.conference.repository.teams.UserInTeamRepository;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.user.User;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TeamsService {
        
    private final UserRepository userRepo;
    
    private final TeamsRepository teamsRepo;

    private final UserInTeamRepository userInTeamRepo;

    private final NotificationService notificationService;

    public TeamsService(UserRepository userRepo, TeamsRepository teamsRepo, UserInTeamRepository userInTeamRepo,NotificationService notificationService) {
        this.userRepo = userRepo;
        this.teamsRepo = teamsRepo;
        this.userInTeamRepo = userInTeamRepo;
        this.notificationService =  notificationService;
    }

    public void saveTeam(Team team,User user){
        team.setLeader(user);
        Team createdTeam = teamsRepo.save(team);
        UserInTeam UIT = new UserInTeam(user,createdTeam);
        userInTeamRepo.save(UIT);

    }

    public void inviteUser(Team team,User user){
        UserInTeam UIT = new UserInTeam(user,team);
        String activationCode = UUID.randomUUID().toString();
        UIT.setActivationCode(activationCode);
        UIT.setIsActive(false);
        notificationService.sendInvitationToTeamNotification(user,team,activationCode);
        userInTeamRepo.save(UIT);
    }

    public void acceptInvite(String code){
        Optional<UserInTeam> userInTeam = userInTeamRepo.findByActivationCode(code);
        if (userInTeam.isPresent()){
            userInTeam.get().setIsActive(true);
            userInTeam.get().setActivationCode(null);
            notificationService.clearUserInviteByCode(code);
        }


    }

    public void declineInvite(String code) {
        Optional<UserInTeam> userInTeam = userInTeamRepo.findByActivationCode(code);
        if (userInTeam.isPresent()){;
            userInTeamRepo.delete(userInTeam.get());
            notificationService.clearUserInviteByCode(code);
        }
    }

    public void deleteUserFromTeam(Team team,String username){
        Optional<UserInTeam> userInTeam = team.getUserInTeam().stream().filter(x -> x.getUser().getUsername().equals(username)).findFirst();
        if(userInTeam.isPresent()){
            notificationService.clearUserInviteByCode(userInTeam.get().getActivationCode());
            userInTeamRepo.delete(userInTeam.get());
        }

    }

    public List<Team> findTeamsByUsername(String username){
        List<Team> teams = teamsRepo.findByUsername(username);
        return teams;
    }

    public List<Team> findTeamsByUsernameAndLeaderStatus(String username){
        User user = userRepo.findByUsername(username).get();
        List<Team> activeTeams = teamsRepo.findByLeader_Id(user.getId());
        return activeTeams;
    }

    public Optional<User> findUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public User findUserByUsernameOrThrown(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new UserProfileNotFoundException());
    }

    public Optional<Team> findTeamById(Long id){
        return teamsRepo.findById(id);
    }

    public Team findTeamByIdOrThrown(Long id){
        return teamsRepo.findById(id).orElseThrow(()->new TeamNotFoundException());
    }

    public void deleteTeam(Team team){
        notificationService.clearInvites(team);
        teamsRepo.delete(team);
    }
}
