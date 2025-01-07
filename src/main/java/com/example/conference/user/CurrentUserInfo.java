package com.example.conference.user;


import java.util.List;

public class CurrentUserInfo {

    public CurrentUserInfo(String username,String profileImg,List<Notification> notificationList) {
        this.username = username;
        this.profileImg = profileImg;
        this.notificationList = notificationList;
    }

    public CurrentUserInfo() {
    }

    private String username;

    private String profileImg;

    private List<Notification> notificationList;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }
}
