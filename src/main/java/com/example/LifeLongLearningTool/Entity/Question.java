package com.example.LifeLongLearningTool.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;

    @ManyToOne
    @JoinColumn(name="topicID", nullable = false)
    private Topic topicID;

    @Column(name = "question")
    private String question;

    @Column(name = "questionType")
    private String questionType;

    public Long getQuestionID()
    {
        return questionID;
    }

    public Topic getTopicID()
    {
        return topicID;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getQuestionType()
    {
        return questionType;
    }
}
