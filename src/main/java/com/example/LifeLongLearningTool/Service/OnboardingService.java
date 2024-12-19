package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.Competency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class OnboardingService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private TopicRepository topicRepository;

    @Autowired
    public OnboardingService(UserRepository userRepository, TopicRepository topicRepository, SubjectRepository subjectRepository)
    {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.subjectRepository = subjectRepository;
    }

    public void finishOnboarding(String uuid, String subjectName, String topicName1, Competency competency1, String topicName2, Competency competency2)
    {
        User user = userRepository.findByUuid(UUID.fromString(uuid));

        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setUserID(user);
        subjectRepository.save(subject);
        subjectRepository.flush();

        System.out.println("ONBOARDING SERVICE::AFTER SUBJECT");
        System.out.println("SUBJECT: " + subject);
        System.out.println("COMPETENCY: " + competency1);
        System.out.println("name:" + topicName1);

        System.out.println("BEFORE topic create");
        Topic topic1 = new Topic();
        System.out.println("BEFORE SET SUBJECT");
        topic1.setSubject(subject);
        System.out.println("BEFORE SET COMPETENCY");
        topic1.setCompetency(competency1);
        System.out.println("BEFORE SET NAME");
        topic1.setName(topicName1);
        System.out.println("BEFORE SAVE TO REPO");
        topicRepository.save(topic1); //errorhapenshere

        System.out.println("ONBOARDING SERVICE::AFTER TOPIC 1");

        System.out.println("ONBOARDING SERVICE::AFTER SUBJECT");
        System.out.println("SUBJECT: " + subject);
        System.out.println("COMPETENCY: " + competency2);
        System.out.println("name:" + topicName2);

        Topic topic2 = new Topic();
        topic2.setSubject(subject);
        topic2.setCompetency(competency2);
        topic2.setName(topicName2);
        topicRepository.save(topic2);

        System.out.println("ONBOARDING SERVICE::AFTER TOPIC2");

        user.setOnboardingFinished(true);
        userRepository.save(user);
        System.out.println("ONBOARDING SERVICE::REPO SAVED");
    }



}
