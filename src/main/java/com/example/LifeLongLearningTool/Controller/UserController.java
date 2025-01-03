package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.UserDTO;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Service.OnboardingService;
import com.example.LifeLongLearningTool.Service.UserService;
import com.example.LifeLongLearningTool.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private OnboardingService onboardingService;

    @GetMapping
    public List<User> getAllUsers()
    {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userServiceImpl.getUserByID(id);
    }

    @GetMapping("/{name}/{id}")
    public void setUserPersonNameById(@PathVariable String name, @PathVariable Long id)
    {
        userServiceImpl.setUserPersonNameByID(id, name);
        System.out.println("LOG SET USER PERSON NAME");
    }

    @GetMapping("/{id}/{status}")
    public void setUserOnboardingStatus(@PathVariable Long id, @PathVariable Boolean status)
    {
        onboardingService.setUserOnboardingStatus(id, status);
        System.out.println("SET USER ONBOARDING STATUS");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO)
    {
        UUID uuid = userDTO.getUuid();
        userServiceImpl.createUser(uuid, userDTO.getName(), userDTO.getEmail());
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/by-uuid/{uuid}")
    public User getUserByUuid(@PathVariable String uuid)
    {
        return userServiceImpl.getUserByUuid(uuid);
    }

    @GetMapping("/onboardingStatus/{uuid}")
    public Boolean getOnboardingStatus(@PathVariable String uuid)
    {
        User user = userServiceImpl.getUserByUuid(uuid);
        return user.getOnboardingFinished();
    }
}