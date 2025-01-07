package com.example.conference.access;

import com.example.conference.exceptions.TeamEditingAccessDeniedException;
import com.example.conference.teams.Team;
import com.example.conference.repository.teams.TeamsRepository;
import com.example.conference.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamAccessVerifier {

    private final TeamsRepository teamsRepo;

    @Autowired
    public TeamAccessVerifier( TeamsRepository teamsRepo) {
        this.teamsRepo = teamsRepo;
    }

    public boolean isTeamLeader(User user, Long teamId){

        Optional<Team> team = teamsRepo.findById(teamId);
        if (team.isPresent()){
            return team.get().getLeader().equals(user);
        }
        return false;
    }

    public boolean isTeamLeader(Authentication authentication, Team team){
        if (!(authentication.getPrincipal() instanceof User user)) return false;
        boolean isTeamLeader = team.getLeader().equals(user);
        if (!isTeamLeader) throw new TeamEditingAccessDeniedException();
        return true;
    }

    public boolean isTeamMember(User user, Long teamId){
        Optional<Team> team = teamsRepo.findById(teamId);
        if (team.isPresent()){
            Set<User> usersInTeam = team.get().getUserInTeam().stream().map(x -> x.getUser()).collect(Collectors.toSet());
            return usersInTeam.contains(user) && !team.get().getLeader().equals(user);
        }
        return false;
    }



}
