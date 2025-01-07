package com.example.conference.contests;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class CriteriaMark {

    public CriteriaMark(Long id, byte mark){
        this.id = id;
        this.mark = mark;
    }

    public CriteriaMark(Criteria criteria) {
        this.mark = 0;
        this.criteria = criteria;
    }

    public CriteriaMark() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private byte mark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "team_record_id")
    private TeamRecord teamRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public TeamRecord getTeamRecord() {
        return teamRecord;
    }

    public void setTeamRecord(TeamRecord teamRecord) {
        this.teamRecord = teamRecord;
    }

    @Override
    public String toString() {
        return "CriteriaMark{" +
                "id=" + id +
                ", mark=" + mark +
                '}';
    }
}
