package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TopicService topicService;

    @Test
    void testGetTopicByID_TypeObject()
    {
        Topic topic = topicRepository.getReferenceById(1L);
        Assertions.assertInstanceOf(Topic.class, topic);
    }

    @Test
    void testCreateTopic_TypeObject()
    {
        User user = userRepository.findById(1L).orElseThrow();

        Subject subject = new Subject();
        subject.setName("Random Subject");
        subject.setUserID(user);
        subjectRepository.save(subject);

        Assertions.assertEquals("Random Subject", subject.getName());
        Assertions.assertNotNull(subject.getUserID());

        Topic topic = new Topic();
        topic.setName("Random Topic");
        topic.setSubject(subject);
        topicRepository.save(topic);

        Assertions.assertEquals("Random Topic", topic.getName());
        Assertions.assertNotNull(topic.getSubject());

        topicRepository.delete(topic);
        subjectRepository.delete(subject);
    }
}