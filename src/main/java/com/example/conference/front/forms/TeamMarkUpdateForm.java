package com.example.conference.front.forms;

import com.example.conference.contests.CriteriaMark;

import java.util.List;
import java.util.Set;

public class TeamMarkUpdateForm {

    public TeamMarkUpdateForm(List<CriteriaMark> criteriaMarkSet,Long conferenceId) {
        this.criteriaMarkSet = criteriaMarkSet;
        this.conferenceId = conferenceId;
    }

    private List<CriteriaMark> criteriaMarkSet;

    private Long conferenceId;

    public List<CriteriaMark> getCriteriaMarkSet() {
        return criteriaMarkSet;
    }

    public void setCriteriaMarkSet(List<CriteriaMark> criteriaMarkSet) {
        this.criteriaMarkSet = criteriaMarkSet;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public String toString() {
        return "TeamMarkUpdateForm{" +
                "criteriaMarkSet=" + criteriaMarkSet +
                '}';
    }
}
