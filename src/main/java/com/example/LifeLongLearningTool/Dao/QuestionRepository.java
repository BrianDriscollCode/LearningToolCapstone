package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopic(Topic topic);
}