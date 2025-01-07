package com.example.conference.service;

import com.example.conference.exceptions.UserInvitationException;
import com.example.conference.teams.Team;
import com.example.conference.teams.UserInTeam;
import com.example.conference.repository.teams.NotificationRepository;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.user.Notification;
import com.example.conference.user.NotificationType;
import com.example.conference.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
@Transactional
public class NotificationService {

    private final UserRepository userRepo;
    private final NotificationRepository notificationRepo;

    public NotificationService(UserRepository userRepo, NotificationRepository notificationRepo) {
        this.userRepo = userRepo;
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> findNotificationsByUsername(String username){
        return notificationRepo.findByNotificationReceiver_Username(username);
    }

    public void sendInvitationToTeamNotification(User user, Team team,String activationCode){
        if (team.userIsPresent(user) && team.userIsActive(user)) throw new UserInvitationException("Пользователь уже в команде");
        if (team.userIsPresent(user)) throw new UserInvitationException("Пользователь уже приглашен в команду");
        Notification notification = new Notification(NotificationType.TEAM_INVITE, user, team, activationCode);
        notificationRepo.save(notification);
    }

    public void clearInvites(Team team){
        Set<UserInTeam> userInTeam = team.getUserInTeam();
        userInTeam.forEach(x-> {
            String activationCode = x.getActivationCode();
            Optional<Notification> notification = notificationRepo.findByUrl(activationCode);
            if(notification.isPresent()) notificationRepo.delete(notification.get());
        });
    }

    public void clearUserInviteByCode(String code){
        Optional<Notification> notification = notificationRepo.findByUrl(code);
        notification.ifPresent(notificationRepo::delete);
    }
}
