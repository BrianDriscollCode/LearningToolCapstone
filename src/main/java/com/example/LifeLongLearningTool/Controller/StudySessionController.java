package com.example.LifeLongLearningTool.Controller;


import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Service.StudySessionService;
import com.example.LifeLongLearningTool.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{uuid}")
    public List<StudySession> getStudySessions(@PathVariable UUID uuid)
    {
        System.out.println("UUID: " + uuid);
        List<StudySession> studySessions = studySessionService.getUserStudySessions(uuid);
        System.out.println("Study sessions: " + studySessions);

        return studySessions;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateSessions(@RequestParam UUID uuid)
    {
        studySessionService.generateStudySession(uuid);
        return ResponseEntity.ok("Success");
    }
}
