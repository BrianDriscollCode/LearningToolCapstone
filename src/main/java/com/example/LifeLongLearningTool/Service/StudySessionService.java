package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.StudySessionRepository;
import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudySessionService {


    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private TopicRepository topicRepository;
    private StudySessionRepository studySessionRepository;


    @Autowired
    public StudySessionService(UserRepository userRepository, SubjectRepository subjectRepository, TopicRepository topicRepository, StudySessionRepository studySessionRepository)
    {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.topicRepository = topicRepository;
        this.studySessionRepository = studySessionRepository;
    }

    public List<StudySession> getAllStudySessions()
    {
        return studySessionRepository.findAll();
    }

    public void generateStudySession(UUID uuid, int howManyDays)
    {
        HashMap<LocalDate, List<StudySession>> studySchedule = new HashMap<>();

        User user = userRepository.findByUuid(uuid);
        //long userID = user.getUserID();

        /*List<Subject> subjects = subjectRepository.findByUserID(userID);

        for (Subject subject: subjects)
        {
            System.out.println(subject.getName());
        }*/

        int daysOnTracker = 2;
        int daysBetweenSessions = 4;
        LocalDate dateOfToday = LocalDate.now();

        System.out.println(howManyDays);
        System.out.println(uuid);
    }
}
