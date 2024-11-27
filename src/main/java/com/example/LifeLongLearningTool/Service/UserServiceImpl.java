package com.example.LifeLongLearningTool.Service;


import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;
}
