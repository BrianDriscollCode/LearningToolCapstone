package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}