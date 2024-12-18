package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.StudySessionRepository;
import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.LearningStatus;
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

    public List<StudySession> getUserStudySessions(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        List<StudySession> sessions = studySessionRepository.findByUserID(user);
        System.out.println("Sessions: " + sessions);
        return studySessionRepository.findByUserID(user);
    }

    // generate initial study sessions during onboarding
    public void generateStudySession(UUID uuid)
    {
        User user = userRepository.findByUuid(uuid);

        List<Subject> subjects = subjectRepository.findByUserUserID(user.getUserID());

        subjects.forEach(subject -> {
            List<Topic> topics = topicRepository.findBySubjectSubjectID(subject.getSubjectID());
            if (topics != null && !topics.isEmpty())
            {
                topics.forEach(topic ->
                {
                    System.out.println("Topic: " + topic.getName());
                    activeStudySessionGenerator(topic);
                });
            }
            else
            {
                System.out.println("No topic in Subject: " + subject.getName());
            }
        });

        user.setInitialDataGenerated(true);
        userRepository.save(user);
    }

    // Enum Learning_Status: ACTIVE
    public void activeStudySessionGenerator(Topic topic)
    {
        LocalDate today = LocalDate.now();
        int totalDaysInMonth = today.lengthOfMonth();
        int currentDay = today.getDayOfMonth();

        int howManyDays = totalDaysInMonth - currentDay;

        int daysOnTracker = 0;

        for (int i = 0; i < howManyDays; i++)
        {
            LocalDate currentDate = today.plusDays(i);

            if (daysOnTracker < 2)
            {
                StudySession newStudySession = new StudySession();
                newStudySession.setUserID(topic.getSubject().getUserID());
                newStudySession.setDate(currentDate);
                newStudySession.setMoreStudyRequired(true);
                newStudySession.setTopicID(topic);
                newStudySession.setCompleted(false);

                studySessionRepository.save(newStudySession);
            }
            else
            {
                daysOnTracker = (daysOnTracker + 1) % 4;
            }
        }


    }

}
