package com.example.conference.front.forms;

import com.example.conference.contests.Conference;
import com.example.conference.contests.Criteria;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NewConferenceForm {


    private LinkedList <Criteria> criteriaList = new LinkedList<>();

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @NotBlank(message = "Введите название")
    private String name;

    @Future(message = "Поле должно содержать дату, которая еще не наступила")
    private LocalDateTime registrationEnd;

    public void addCriteria(Criteria criteria){
        criteriaList.add(criteria);
    }

    public Conference toConference(){
        Conference conference = new Conference(0L, name, description, registrationEnd);
        conference.setCriteriaSet(new HashSet<>(criteriaList));
        conference.getCriteriaSet().forEach(x-> x.setConference(conference));
        return conference;
    }

    public NewConferenceForm() {
    }

    public LinkedList<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(LinkedList<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(LocalDateTime registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

}
