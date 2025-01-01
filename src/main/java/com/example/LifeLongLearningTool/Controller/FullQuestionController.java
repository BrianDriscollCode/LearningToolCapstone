package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Entity.Answer;
import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Service.FullQuestionService;
import com.example.LifeLongLearningTool.Utils.KeyValuePair;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.List;

@RestController
@RequestMapping("/api/fullQuestion")
public class FullQuestionController {

    @Autowired
    FullQuestionService fullQuestionService;

    @GetMapping("/get/{topicID}")
    public List<KeyValuePair<Question, Answer>> getQuestions(@PathVariable Long topicID)
    {
        List<KeyValuePair<Question, Answer>> questions = fullQuestionService.getQuestions(topicID);
        return questions;
    }

    @PostMapping("/post/{question}/{answer}/{topicID}")
    public ResponseEntity<String> postFullQuestion(@PathVariable String question, @PathVariable String answer, @PathVariable Long topicID)
    {
        fullQuestionService.postFullQuestion(question, answer, topicID);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/edit/{questionID}/{question}/{answerID}/{answer}")
    public ResponseEntity<String> editFullQuestion(@PathVariable Long questionID, @PathVariable String question, @PathVariable Long answerID, @PathVariable String answer)
    {
        fullQuestionService.editFullQuestion(questionID, question, answerID, answer);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/delete/{questionID}/{answerID}")
    public ResponseEntity<String> deleteFullQuestion(@PathVariable Long questionID, @PathVariable Long answerID)
    {
        fullQuestionService.deleteFullQuestion(questionID, answerID);
        return ResponseEntity.ok("Deletion Successful");
    }


}
