package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findBySubjectSubjectID(Long subjectID);
}