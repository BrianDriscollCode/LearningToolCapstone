package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.TopicDTO;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Service.StudySessionService;
import com.example.LifeLongLearningTool.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private StudySessionService studySessionService;

    @GetMapping
    public List<Topic> getAllTopics()
    {
        return topicService.getAllTopics();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody TopicDTO topicDTO)
    {
        System.out.println(topicDTO.getName() + " " + topicDTO.getUuid() + " " + topicDTO.getCompetency());
        topicService.createTopic(topicDTO.getName(), topicDTO.getCompetency(), topicDTO.getSubjectID());
        return ResponseEntity.ok("Topic created successfully");
    }

    @PostMapping("/createTopic/{id}/{name}/{competency}/{learning_status}")
    public ResponseEntity<String> createTopic(@PathVariable Long id, @PathVariable String name, @PathVariable String competency, @PathVariable String learning_status)
    {
        System.out.println("Subjectid: " + id);
        System.out.println("Name: " + name);
        System.out.println("Competency id: " + competency);
        System.out.println("Learning Status: " + learning_status);

        Topic topic = topicService.createTopicWithLearningStatus(name, competency, id, learning_status);
        //Eventually generate for different learning_status after creating functions
        studySessionService.activeStudySessionGenerator(topic);

        return ResponseEntity.ok("Success");
    }

}
