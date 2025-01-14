package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.QuestionRepository;
import com.example.LifeLongLearningTool.Entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }
}
