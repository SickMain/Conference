package com.example.conference.repository.teams;

import com.example.conference.user.Notification;
import com.example.conference.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification,Long> {

     Optional<Notification> findByUrl(String url);

     List<Notification> findByNotificationReceiver(User user);
     List<Notification> findByNotificationReceiver_Username(String username);
}
