package com.example.LifeLongLearningTool.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="topicID", nullable = false)
    @JsonIgnore
    private Topic topic;

    @Column(name = "question")
    private String question;

    public Long getQuestionID()
    {
        return questionID;
    }
    public void setQuestionID(Long questionID) { this.questionID = questionID; }

    public Topic getTopic()
    {
        return topic;
    }
    public void setTopic(Topic topic) { this.topic = topic; }

    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question) { this.question = question; }
}
