package com.example.LifeLongLearningTool.Controller;


import com.example.LifeLongLearningTool.Entity.Answer;
import com.example.LifeLongLearningTool.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers()
    {
        return answerService.getAllAnswers();
    }
}
