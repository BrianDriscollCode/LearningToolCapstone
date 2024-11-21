package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
