package com.example.LifeLongLearningTool.Service;


import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserPersonNameByID(Long id, String name) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setName(name);
        userRepository.save(user);
    }

    public void createUser(UUID uuid, String name, String email)
    {
        User user = new User();
        user.setUUID(uuid);
        user.setName(name);
        user.setEmail(email);
        user.setOnboardingFinished(false);
        userRepository.save(user);
    }

}
