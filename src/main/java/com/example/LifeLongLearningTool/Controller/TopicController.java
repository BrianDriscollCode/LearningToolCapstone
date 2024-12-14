package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.TopicDTO;
import com.example.LifeLongLearningTool.Entity.Topic;
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

    @GetMapping
    public List<Topic> getAllTopics()
    {
        return topicService.getAllTopics();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody TopicDTO topicDTO)
    {
        System.out.println(topicDTO.getName() + " " + topicDTO.getUuid() + " " + topicDTO.getCompetency());
        topicService.createTopic(topicDTO.getName(), topicDTO.getCompetency(), topicDTO.getUuid(), topicDTO.getSubjectID());
        return ResponseEntity.ok("Topic created successfully");
    }

}
