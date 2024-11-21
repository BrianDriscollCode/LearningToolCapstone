package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerID;

    @ManyToOne
    @JoinColumn(name = "questionID", nullable = false)
    private Question question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    public Long getAnswerID()
    {
        return answerID;
    }

    public Question getQuestionID()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public Boolean getIsCorrect()
    {
        return isCorrect;
    }
}
