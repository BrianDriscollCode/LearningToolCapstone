package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.AnswerRepository;
import com.example.LifeLongLearningTool.Entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers()
    {
        return answerRepository.findAll();
    }
}
