package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.AnswerRepository;
import com.example.LifeLongLearningTool.Dao.QuestionRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Entity.Answer;
import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Utils.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class FullQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TopicRepository topicRepository;

    public List<KeyValuePair<Question, Answer>> getQuestions(Long topicID)
    {
        Topic topic = topicRepository.getReferenceById(topicID);

        List<Question> questionList = questionRepository.findByTopic(topic);
        List<KeyValuePair<Question, Answer>> pairs = new ArrayList<>();

        for (Question question: questionList)
        {
            Answer answer = answerRepository.findByQuestion(question);

            pairs.add(new KeyValuePair<>(question, answer));
        }

        return pairs;
    }

    public void postFullQuestion(String question,String answer,Long topicID)
    {
        Topic topic = topicRepository.getReferenceById(topicID);

        Question questionObject = new Question();
        questionObject.setQuestion(question);
        questionObject.setTopic(topic);
        questionRepository.save(questionObject);

        Answer answerObject = new Answer();
        answerObject.setAnswer(answer);
        answerObject.setQuestion(questionObject);
        answerRepository.save(answerObject);
    }

    public void editFullQuestion(Long questionID, String question, Long answerID, String answer)
    {
        Question questionObject = questionRepository.findById(questionID).orElseThrow(() -> new RuntimeException("Could not find with questionID: " + questionID));
        Answer answerObject = answerRepository.findById(answerID).orElseThrow(() -> new RuntimeException("Could not find answer with answerID: " + answerID));

        questionObject.setQuestion(question);
        questionRepository.save(questionObject);

        answerObject.setAnswer(answer);
        answerRepository.save(answerObject);
    }

    public void deleteFullQuestion(Long questionID, Long answerID)
    {
        Question question = questionRepository.findById(questionID).orElseThrow();
        Answer answer = answerRepository.findById(answerID).orElseThrow();

        questionRepository.delete(question);
        answerRepository.delete(answer);
    }
}
