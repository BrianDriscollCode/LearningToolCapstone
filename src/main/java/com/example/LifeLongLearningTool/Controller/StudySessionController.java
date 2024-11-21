package com.example.LifeLongLearningTool.Controller;


import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Service.StudySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/studySessions")
public class StudySessionController {

    @Autowired
    private StudySessionService studySessionService;

    @GetMapping
    public List<StudySession> getAllStudySessions()
    {
        return studySessionService.getAllStudySessions();
    }
}
