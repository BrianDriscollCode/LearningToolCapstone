package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public abstract class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByID(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public User getUserByUuid(String uuid)
    {
        UUID uuidReturnObject = UUID.fromString(uuid);
        System.out.println(userRepository.findByUuid(uuidReturnObject));
        return userRepository.findByUuid(uuidReturnObject);
    }

    public void deleteUserWithUUID(UUID uuid)
    {
        User user = userRepository.findByUuid(uuid);
        userRepository.delete(user);;
    }

    public void setUserOnboardingStatus(Long id, Boolean status)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found with ID: " + id));
        user.setOnboardingFinished(status);
        userRepository.save(user);
    }

}
