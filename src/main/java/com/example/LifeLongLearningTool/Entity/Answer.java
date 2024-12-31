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

    public Long getAnswerID()
    {
        return answerID;
    }
    public void setAnswerID(Long answerID) { this.answerID = answerID; }

    public Question getQuestion()
    {
        return question;
    }
    public void setQuestion(Question question) { this.question = question; }

    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer) { this.answer = answer; }
}
