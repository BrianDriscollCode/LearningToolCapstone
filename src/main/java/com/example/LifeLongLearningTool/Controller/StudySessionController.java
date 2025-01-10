package com.example.LifeLongLearningTool.Controller;


import com.example.LifeLongLearningTool.Dto.LearningMapDataDTO;
import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.Competency;
import com.example.LifeLongLearningTool.Service.StudySessionService;
import com.example.LifeLongLearningTool.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/reschedule/{studySessionID}/{date}")
    public ResponseEntity<String> rescheduleStudySessionDate(@PathVariable Long studySessionID, @PathVariable LocalDate date)
    {
        System.out.println(studySessionID + " -studySessionID in controller");
        System.out.println(date + "-localDate in controller");
        studySessionService.rescheduleDate(studySessionID, date);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/delete/{studySessionID}")
    public ResponseEntity<String> deleteStudySession(@PathVariable Long studySessionID)
    {
        System.out.println(studySessionID + " -studySessionID in controller");
        studySessionService.deleteStudySession(studySessionID);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/learningMapInfo/{uuid}")
    public List<LearningMapDataDTO> getLearningMapInfo(@PathVariable UUID uuid)
    {
        System.out.println("UUID::getLearningMapInfo::" + uuid);
        return studySessionService.getLearningMapInfo(uuid);
    }

    @PostMapping("/complete/{studySessionID}")
    public ResponseEntity<String> completeStudySession(@PathVariable Long studySessionID)
    {
        studySessionService.completeStudySession(studySessionID);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/studySessionsCompleted/{uuid}")
    public int studySessionsCompleted(@PathVariable UUID uuid)
    {
        System.out.println("StudySessions Completed - UUID: " + uuid);
        System.out.println("StudySessions Completed: run");
        int numberOfCompletedStudySessions = studySessionService.getCompletedSessions(uuid);
        System.out.println("StudySessions Completed: " + numberOfCompletedStudySessions);
        return numberOfCompletedStudySessions;
    }
}
