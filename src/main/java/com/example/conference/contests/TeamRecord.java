package com.example.conference.contests;

import com.example.conference.teams.Team;
import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class TeamRecord implements Comparable<TeamRecord> {

    public TeamRecord(Long id,String name, Long leaderId){
        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
    }

    public TeamRecord(Team toRecord){
        this.name = toRecord.getName();
        this.leaderId = toRecord.getLeader().getId();
    }

    public TeamRecord(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long leaderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "teamRecord",fetch = FetchType.LAZY)
    @OrderBy("criteria.id")
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

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }


    public void setDefaultMarks(Set<Criteria> criteriaSet){
        criteriaSet.forEach(criteria ->{
            CriteriaMark criteriaMark = new CriteriaMark(criteria);
            criteriaMark.setTeamRecord(this);
            this.criteriaMarks.add(criteriaMark);
        });
    }

    public Set<CriteriaMark> getCriteriaMarks() {
        return criteriaMarks;
    }

    public void setCriteriaMarks(Set<CriteriaMark> criteriaMarks) {
        this.criteriaMarks = criteriaMarks;
    }

    public int getSummaryMark(){
        return this.criteriaMarks.stream().map(criteriaMark -> criteriaMark.getMark()).mapToInt(value -> (int) value).sum();
    }

    @Override
    public int compareTo(TeamRecord o) {
        return Integer.compare(o.getSummaryMark(),this.getSummaryMark());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamRecord that = (TeamRecord) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(leaderId, that.leaderId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (leaderId != null ? leaderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeamRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leaderId=" + leaderId +
                '}';
    }
}
