package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }
}
