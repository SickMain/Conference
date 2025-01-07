package com.example.conference.aop.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

@Deprecated
public class TeamAccessVerifierPointcuts {

    @Pointcut("execution(public String com.example.conference.controllers.teams.TeamEditPageController.editTeamPage(..))")
    public void editTeamFormPointcut(){}

    @Pointcut("execution(public String com.example.conference.controllers.teams.TeamViewPageController.viewTeamPage(..))")
    public void viewTeamFormPointcut(){}
}
