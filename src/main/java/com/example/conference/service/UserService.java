package com.example.conference.service;

import com.example.conference.exceptions.UserProfileNotFoundException;
import com.example.conference.repository.user.UserRepository;
import com.example.conference.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@Transactional
public class UserService {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${defaultUserProfileImg}")
    private String defaultUserProfileImg;

    private final UserRepository userRepo;


    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findUserById(Long id){
        return  userRepo.findById(id);
    }

    public Optional<User> findUserByUsername(String username){
        return  userRepo.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email){
        return  userRepo.findByEmail(email);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void changeProfileImg(String username,MultipartFile file){
        userRepo.findByUsername(username).map(user -> {
            if (!file.isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                if (!Objects.equals(user.getProfileImg(), defaultUserProfileImg)) {
                    File toDelete = new File(uploadPath + "/" + user.getProfileImg());
                    toDelete.delete();
                }
                String resultFileName = user.getUsername() + "." + file.getOriginalFilename();
                user.setProfileImg(resultFileName);
                try {
                    file.transferTo(new File(uploadPath + "/" + resultFileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return user;
        }).orElseThrow(() -> new UserProfileNotFoundException());
    }

    public void dropProfileImg(String username){
        userRepo.findByUsername(username).map(user -> {
            if (!Objects.equals(user.getProfileImg(), defaultUserProfileImg)) {
                File toDelete = new File(uploadPath + "/" + user.getProfileImg());
                toDelete.delete();
                user.setProfileImg(null);
            }
            return null;
        });
    }

}
