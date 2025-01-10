package com.example.LifeLongLearningTool.Dao;


import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUuid(UUID uuid);
}
