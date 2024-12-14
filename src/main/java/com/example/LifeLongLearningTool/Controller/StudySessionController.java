package com.example.LifeLongLearningTool.Controller;


import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Service.StudySessionService;
import com.example.LifeLongLearningTool.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/studySessions")
public class StudySessionController {

    private StudySessionService studySessionService;
    private TopicService topicService;

    @Autowired
    public StudySessionController(StudySessionService studySessionService, TopicService topicService)
    {
        this.studySessionService = studySessionService;
        this.topicService = topicService;
    }

    @GetMapping
    public List<StudySession> getAllStudySessions()
    {
        return studySessionService.getAllStudySessions();
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateSessions(@RequestParam UUID uuid, @RequestParam int howManyDays)
    {
        studySessionService.generateStudySession(uuid, howManyDays);
        return ResponseEntity.ok("test");
    }
}
