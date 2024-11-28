package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.UserDTO;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserByID(id);
    }

    @GetMapping("/{name}/{id}")
    public void setUserPersonNameById(@PathVariable String name, @PathVariable Long id)
    {
        userService.setUserPersonNameByID(id, name);
        System.out.println("LOG SET USER PERSON NAME");
    }

    @GetMapping("/{id}/{status}")
    public void setUserOnboardingStatus(@PathVariable Long id, @PathVariable Boolean status)
    {
        userService.setUserOnboardingStatus(id, status);
        System.out.println("SET USER ONBOARDING STATUS");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO)
    {
        UUID uuid = userDTO.getUuid();
        userService.createUser(uuid, userDTO.getName(), userDTO.getEmail());
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/by-uuid/{uuid}")
    public User getUserByUuid(@PathVariable String uuid)
    {
        return userService.getUserByUuid(uuid);
    }

    @GetMapping("/onboardingStatus/{uuid}")
    public Boolean getOnboardingStatus(@PathVariable String uuid)
    {
        User user = userService.getUserByUuid(uuid);
        return user.getOnboardingFinished();
    }
}