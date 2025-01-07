package com.example.conference.contests;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Criteria {

    public Criteria(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Criteria(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conference_id")
    private Conference conference;

    @OneToMany(mappedBy = "criteria",fetch = FetchType.LAZY)
    private Set<CriteriaMark> criteriaMarks = new HashSet<>();

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

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<CriteriaMark> getCriteriaMarks() {
        return criteriaMarks;
    }

    public void setCriteriaMarks(Set<CriteriaMark> criteriaMarks) {
        this.criteriaMarks = criteriaMarks;
    }


}
