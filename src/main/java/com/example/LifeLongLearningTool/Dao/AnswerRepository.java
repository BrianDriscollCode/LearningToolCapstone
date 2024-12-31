package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Answer;
import com.example.LifeLongLearningTool.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByQuestion(Question question);
}
