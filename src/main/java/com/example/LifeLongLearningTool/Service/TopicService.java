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

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private TopicRepository topicRepository;

    @Autowired
    public TopicService(UserRepository userRepository, TopicRepository topicRepository, SubjectRepository subjectRepository)
    {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Topic> getAllTopics()
    {
        return topicRepository.findAll();
    }

    public void createTopic(String name, String competency, String uuid, Long subjectId)
    {
        //User user = userRepository.findByUuid(UUID.fromString(uuid));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Could not find Subject with ID: " + subjectId));
        Competency comp = Competency.valueOf(competency.toUpperCase());

        Topic topic = new Topic();
        topic.setName(name);
        topic.setSubjectID(subject);
        topic.setCompetency(comp);
        topicRepository.save(topic);
    }
}
