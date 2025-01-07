package com.example.conference.aop.aspects;

import com.example.conference.access.TeamAccessVerifier;
import com.example.conference.teams.Team;
import com.example.conference.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.security.Principal;

@Aspect
@Deprecated
public class TeamAccessVerifierAspect {


    private final HttpServletRequest request;
    private final TeamAccessVerifier teamAccessVerifier;


    @Autowired
    public TeamAccessVerifierAspect(TeamAccessVerifier teamAccessVerifier,HttpServletRequest request) {
        this.teamAccessVerifier = teamAccessVerifier;
        this.request = request;
    }

    @Before(value = "com.example.conference.aop.pointcuts.TeamAccessVerifierPointcuts.viewTeamFormPointcut() && args(model,id)",
            argNames = "model,id")
    public void viewTeamAccessVerifyingAdvice(Model model, Long id){
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userPrincipal instanceof User user) {
            if (teamAccessVerifier.isTeamLeader(user, id)) {
                model.addAttribute("canEditTeam", true);
            }
            else if (teamAccessVerifier.isTeamMember(user, id)) {
                model.addAttribute("canLeaveTeam", true);
            }
        }
    }

    @Around(value = "com.example.conference.aop.pointcuts.TeamAccessVerifierPointcuts.editTeamFormPointcut() && args(model,team)",
            argNames = "pjp,model,team")
    public Object editTeamAccessVerifyingAdvice(ProceedingJoinPoint pjp,Model model, Team team) throws Throwable {

//        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(!(userPrincipal instanceof User user) || !teamAccessVerifier.isTeamLeader(user,team)){
//            FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
//            flashMap.put("alertMessage","У вас нет доступа к редактированию этой команды");
//            return "redirect:/";
//        }
        return pjp.proceed();
    }


}
