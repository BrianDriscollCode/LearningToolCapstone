package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.Competency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void finishOnboarding(String uuid, String subjectName, String topicName1, String competency1, String topicName2, String competency2)
    {
        User user = userRepository.findByUuid(UUID.fromString(uuid));

        Competency comp1 = Competency.valueOf(competency1.toUpperCase());
        Competency comp2 = Competency.valueOf(competency2.toUpperCase());

        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setUser(user);
        subjectRepository.save(subject);

        Topic topic1 = new Topic();
        topic1.setSubject(subject);
        topic1.setCompetency(comp1);
        topic1.setName(topicName1);
        topicRepository.save(topic1);

        Topic topic2 = new Topic();
        topic2.setSubject(subject);
        topic2.setCompetency(comp2);
        topic2.setName(topicName2);
        topicRepository.save(topic2);

        user.setOnboardingFinished(true);
        userRepository.save(user);
    }



}
