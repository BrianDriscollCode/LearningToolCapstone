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
    UserServiceImpl userServiceImpl;

    @Autowired
    OnboardingService onboardingService;

    // Function: getUserByID()
    @Test
    void testGetUserByID_TypeObject()
    {
        User user = userServiceImpl.getUserByID(1L);
        Assertions.assertInstanceOf(User.class, user);
    }

    @Test
    void testGetUserByID_TypeName()
    {
        User user = userServiceImpl.getUserByID(1L);
        Assertions.assertInstanceOf(String.class, user.getName());
    }

    @Test
    void testGetUserByID_TypeOnboarding()
    {
        User user = userServiceImpl.getUserByID(1L);
        Assertions.assertInstanceOf(Boolean.class, user.getOnboardingFinished());
    }

    @Test
    void testGetUserByID_TypeUuid()
    {
        User user = userServiceImpl.getUserByID(1L);
        Assertions.assertInstanceOf(UUID.class, user.getUUID());
    }

    //Function: getUserByUUID()
    @Test
    void testGetUserByUUID_TypeObject()
    {
        UUID uuid = userServiceImpl.getUserByID(1L).getUUID();
        User user = userServiceImpl.getUserByUuid(uuid.toString());
        Assertions.assertInstanceOf(User.class, user);
    }

    // Function: setUserOnboardingStatus
    @Test
    void testSetUserOnboardingStatus()
    {
        User user = userServiceImpl.getUserByID(1L);
        boolean currentStatus = user.getOnboardingFinished();

        onboardingService.setUserOnboardingStatus(1L, !currentStatus);
        User updatedUser = userServiceImpl.getUserByID(1L);
        Assertions.assertEquals(!currentStatus, updatedUser.getOnboardingFinished());

        onboardingService.setUserOnboardingStatus(1L, currentStatus);
    }

    // Function: createUser && deleteUserWithUUID
    @Test
    void testCreateUserDeleteUser()
    {
        UUID uuid = UUID.randomUUID();
        userServiceImpl.createUser(uuid, "Jake", "Random07@gmail.com");

        User user = userServiceImpl.getUserByUuid(uuid.toString());
        Assertions.assertInstanceOf(User.class, user);
        Assertions.assertEquals(user.getUUID(), uuid);
        Assertions.assertEquals(user.getName(), "Jake");
        Assertions.assertEquals(user.getEmail(), "Random07@gmail.com");

        userServiceImpl.deleteUserWithUUID(uuid);
        User deletedUser = userServiceImpl.getUserByUuid(uuid.toString());
        Assertions.assertNull(deletedUser);
    }

}