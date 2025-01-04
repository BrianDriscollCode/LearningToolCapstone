package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.EmployeeRepository;
import com.example.LifeLongLearningTool.Entity.Employee;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public abstract class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    public abstract void createUser(UUID uuid, String name, String email);
}
