package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUserID(User user);
}