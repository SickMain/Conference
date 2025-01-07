/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.contests;

import com.example.conference.teams.UserInTeam;
import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;
import org.springframework.data.web.SortDefault;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Entity
public class Conference {

    public Conference(Long id, String name, String description, LocalDateTime registrationClosing) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registrationClosing = registrationClosing;
    }
    public Conference() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String name;
    
    @Column(columnDefinition = "text")
    private String description;

    private LocalDateTime registrationClosing;

    @OneToMany(mappedBy = "conference",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @OrderBy("id ASC")
    private Set<Criteria> criteriaSet = new HashSet<>();


    @OneToMany(mappedBy = "conference",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    private Set<TeamRecord> teamRecords = new HashSet<>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
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

    public LocalDateTime getRegistrationClosing() {
        return registrationClosing;
    }

    public void setRegistrationClosing(LocalDateTime registrationClosing) {
        this.registrationClosing = registrationClosing;
    }

    public Set<Criteria> getCriteriaSet() {
        return criteriaSet;
    }

    public void setCriteriaSet(Set<Criteria> criteriaSet) {
        this.criteriaSet = criteriaSet;
    }

    public Set<TeamRecord> getTeamRecords() {
        return teamRecords;
    }

    public void setTeamRecords(TreeSet<TeamRecord> teamRecords) {
        this.teamRecords = teamRecords;
    }
}
