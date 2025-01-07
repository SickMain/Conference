package com.example.conference.contests;

import com.example.conference.teams.UserInTeam;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UserInTeamRecord {

    public UserInTeamRecord(Long id, Long userId){
        this.id = id;
        this.userId = userId;
    }

    public UserInTeamRecord(UserInTeam userInTeamToRecord) {
     this.userId = userInTeamToRecord.getId().getUserId();
     this.username = userInTeamToRecord.getUser().getUsername();
    }

    public UserInTeamRecord(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String username;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "team_record_id")
    private TeamRecord teamRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TeamRecord getTeamRecord() {
        return teamRecord;
    }

    public void setTeamRecord(TeamRecord teamRecord) {
        this.teamRecord = teamRecord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInTeamRecord that = (UserInTeamRecord) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
