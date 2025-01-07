package com.example.conference.advice;

import com.example.conference.exceptions.UserProfileNotFoundException;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.service.NotificationService;
import com.example.conference.user.CurrentUserInfo;
import com.example.conference.user.Notification;
import com.example.conference.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@ControllerAdvice(annotations = Controller.class)
public class UserInfoAdvice {

    private final UserRepository userRepository;

    private final NotificationService notificationService;

    @Value("${defaultUserProfileImg}")
    private String defaultUserProfileImg;

    public UserInfoAdvice(UserRepository userRepository,NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @ModelAttribute("currentUserInfo")
    public CurrentUserInfo getUser(@AuthenticationPrincipal User user){
        if(user == null) return new CurrentUserInfo();
        String username = user.getUsername();
        List<Notification> notificationList = notificationService.findNotificationsByUsername(username);
        Optional<String> profileImg = userRepository.findProfileImgByUsername(username);
        return new CurrentUserInfo(username,profileImg.orElse(defaultUserProfileImg),notificationList);
    }
}
