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

    @Autowired
    UserServiceImpl userServiceImpl;

    // Function: getUserByID()
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

    //Function: getUserByUUID()
    @Test
    void testGetUserByUUID_TypeObject()
    {
        UUID uuid = userService.getUserByID(1L).getUUID();
        User user = userService.getUserByUuid(uuid.toString());
        Assertions.assertInstanceOf(User.class, user);
    }

    // Function: setUserPersonNameByID
    @Test
    void testSetUserPersonNameByID()
    {
        User user1 = userService.getUserByID(1L);
        String tempName = user1.getName();

        userServiceImpl.setUserPersonNameByID(1L, "John");
        Assertions.assertEquals("John", user1.getName());

        userServiceImpl.setUserPersonNameByID(1L, tempName);
        Assertions.assertEquals(tempName, user1.getName());
    }

    // Function: setUserOnboardingStatus
    @Test
    void testSetUserOnboardingStatus()
    {
        User user = userService.getUserByID(1L);
        boolean currentStatus = user.getOnboardingFinished();

        userService.setUserOnboardingStatus(1L, !currentStatus);
        User updatedUser = userService.getUserByID(1L);
        Assertions.assertEquals(!currentStatus, updatedUser.getOnboardingFinished());

        userService.setUserOnboardingStatus(1L, currentStatus);
    }

    // Function: createUser && deleteUserWithUUID
    @Test
    void testCreateUserDeleteUser()
    {
        UUID uuid = UUID.randomUUID();
        userServiceImpl.createUser(uuid, "Jake", "Random07@gmail.com");

        User user = userService.getUserByUuid(uuid.toString());
        Assertions.assertInstanceOf(User.class, user);
        Assertions.assertEquals(user.getUUID(), uuid);
        Assertions.assertEquals(user.getName(), "Jake");
        Assertions.assertEquals(user.getEmail(), "Random07@gmail.com");

        userService.deleteUserWithUUID(uuid);
        User deletedUser = userService.getUserByUuid(uuid.toString());
        Assertions.assertNull(deletedUser);
    }

}