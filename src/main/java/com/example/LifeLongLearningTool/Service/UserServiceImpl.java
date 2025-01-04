package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends UserService
{

    @Override
    public void createUser(UUID uuid, String name, String email)
    {
        User user = new User();
        user.setUUID(uuid);
        user.setName(name);
        user.setEmail(email);
        user.setOnboardingFinished(false);
        userRepository.save(user);
    }

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

    public void setUserPersonNameByID(Long id, String name) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setName(name);
        userRepository.save(user);
    }

}
