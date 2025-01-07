/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.teams;

import com.example.conference.user.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
public class Team {

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {
        this.id = null;
        this.name = null;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "team",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @OrderBy("id")
    Set <UserInTeam> userInTeam = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leader_id")
    private User leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserInTeam> getUserInTeam() {
        return userInTeam;
    }

    public void setUserInTeam(Set<UserInTeam> userInTeam) {
        this.userInTeam = userInTeam;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Set<UserInTeam> getActiveUsersWithoutLeader(){
        return userInTeam.stream().filter(x-> x.getUser() != leader && x.isActive()).collect(Collectors.toSet());
    }
    public Set<UserInTeam> getInvitedUsersWithoutLeader(){
        return userInTeam.stream().filter(x-> x.getUser() != leader && !x.isActive()).collect(Collectors.toSet());
    }

    public boolean userIsPresent(User user){
        return userInTeam.stream().map(userInTeam -> userInTeam.getUser()).collect(Collectors.toSet()).contains(user);
    }

    public boolean userIsActive(User user){
        return userInTeam.stream()
                .filter(userInTeam -> userInTeam.getUser().equals(user) && userInTeam.isActive())
                .map(userInTeam -> userInTeam.getUser())
                .collect(Collectors.toSet())
                .contains(user);
    }

}
