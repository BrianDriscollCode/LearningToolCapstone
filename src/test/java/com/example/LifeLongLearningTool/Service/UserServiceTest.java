package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    // Testing return types of database fields with function: getUserByID()
    @Test
    void testGetUserByID_TypeObject()
    {
        User user = userService.getUserByID(1L);
        Assertions.assertInstanceOf(User.class, user);
    }

    @Test
    void testGetUserByID_TypeName()
    {
        User user = userService.getUserByID(1L);
        Assertions.assertInstanceOf(String.class, user.getName());
    }

    @Test
    void testGetUserByID_TypeOnboarding()
    {
        User user = userService.getUserByID(1L);
        Assertions.assertInstanceOf(Boolean.class, user.getOnboardingFinished());
    }

    @Test
    void testGetUserByID_TypeUuid()
    {
        User user = userService.getUserByID(1L);
        Assertions.assertInstanceOf(UUID.class, user.getUUID());
    }

    //Testing return type of function: getUserByUUID()
    @Test
    void testGetUserByUUID_TypeObject()
    {
        UUID uuid = userService.getUserByID(1L).getUUID();
        User user = userService.getUserByUuid(uuid.toString());
        Assertions.assertInstanceOf(User.class, user);
    }

    @Test
    void testSetUserPersonNameByID()
    {
        User user1 = userService.getUserByID(1L);
        String tempName = user1.getName();

        userService.setUserPersonNameByID(1L, "John");
        User user2 = userService.getUserByID(1L);
        Assertions.assertEquals("John", user2.getName());

        userService.setUserPersonNameByID(1L, tempName);
    }





}