package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.StudySessionRepository;
import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Dto.LearningMapDataDTO;
import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.LearningStatus;
import com.example.LifeLongLearningTool.Utils.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
        return sessions;
    }

    public void rescheduleDate(Long studySessionID, LocalDate date)
    {
        System.out.println(studySessionID + "- studySessionID");
        StudySession studySession = studySessionRepository.findByStudySessionID(studySessionID);

        studySession.setDate(date);
        studySessionRepository.save(studySession);
    }

    public void deleteStudySession(Long studySessionID)
    {
        StudySession studySession = studySessionRepository.findByStudySessionID(studySessionID);
        studySessionRepository.delete(studySession);
    }

    // Getting the info for subject and <topic, number of sessions>
    public List<LearningMapDataDTO> getLearningMapInfo(UUID uuid)
    {
        User user =  userRepository.findByUuid(uuid);
        List<Subject> subjects = subjectRepository.findByUserUserID(user.getUserID());

        List<LearningMapDataDTO> learningMapInfoList = new ArrayList<>();

        for (Subject subject : subjects)
        {
            List<Topic> topics = topicRepository.findBySubjectSubjectID(subject.getSubjectID());
            List<KeyValuePair<Topic, Integer>> topicSessionPairs = new ArrayList<>();
            for (Topic topic : topics)
            {
                List<StudySession> studySessions = studySessionRepository.findByTopicID(topic);
                KeyValuePair<Topic, Integer> topicSessionPair = new KeyValuePair<>(topic, studySessions.size());
                topicSessionPairs.add(topicSessionPair);
            }

            LearningMapDataDTO learningMapObject = new LearningMapDataDTO(subject, topicSessionPairs);
            learningMapInfoList.add(learningMapObject);
        }

        return learningMapInfoList;
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

        int howManyDays = 31;

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
                daysOnTracker += 1;
            }
            else
            {
                daysOnTracker = (daysOnTracker + 1) % 4;
            }
        }
    }

    // Enum Learning_Status: REVIEW
    public void reviewStudySessionGenerator(Topic topic)
    {
        LocalDate today = LocalDate.now();

        int howManyDays = 31;
        int daysOnTracker = 0;
        int daysBetweenSessions = 8;

        for (int i = 0; i < howManyDays; i++) {
            LocalDate currentDate = today.plusDays(i);

            if (daysOnTracker == 0) {
                StudySession newStudySession = new StudySession();
                newStudySession.setUserID(topic.getSubject().getUserID());
                newStudySession.setDate(currentDate);
                newStudySession.setMoreStudyRequired(true);
                newStudySession.setTopicID(topic);
                newStudySession.setCompleted(false);

                studySessionRepository.save(newStudySession);
                daysOnTracker += 1;
            } else if (daysOnTracker == daysBetweenSessions) {
                daysOnTracker = 0;
            } else {
                daysOnTracker += 1;
            }
        }
    }

    // Enum Learning_Status: MAINTAIN
    public void maintainStudySessionGenerator(Topic topic)
    {
        LocalDate today = LocalDate.now();

        int howManyDays = 31;
        int daysOnTracker = 0;
        int daysBetweenSessions = 4;

        for (int i = 0; i < howManyDays; i++)
        {
            LocalDate currentDate = today.plusDays(i);

            if (daysOnTracker == 3)
            {
                StudySession newStudySession = new StudySession();
                newStudySession.setUserID(topic.getSubject().getUserID());
                newStudySession.setDate(currentDate);
                newStudySession.setMoreStudyRequired(true);
                newStudySession.setTopicID(topic);
                newStudySession.setCompleted(false);

                studySessionRepository.save(newStudySession);
                daysOnTracker += 1;
            }
            else if (daysOnTracker == daysBetweenSessions)
            {
                daysOnTracker = 0;
            }
            else
            {
                daysOnTracker += 1;
            }
        }
    }

    // Enum Learning_Status: GROWTH
    public void growthStudySessionGenerator(Topic topic)
    {
        LocalDate today = LocalDate.now();

        int howManyDays = 31;

        for (int i = 0; i < howManyDays; i++)
        {
            LocalDate currentDate = today.plusDays(i);

            if (currentDate.getDayOfWeek() != DayOfWeek.SUNDAY)
            {
                StudySession newStudySession = new StudySession();
                newStudySession.setUserID(topic.getSubject().getUserID());
                newStudySession.setDate(currentDate);
                newStudySession.setMoreStudyRequired(true);
                newStudySession.setTopicID(topic);
                newStudySession.setCompleted(false);

                studySessionRepository.save(newStudySession);
            }
        }
    }

}
