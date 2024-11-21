package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Study_Sessions")
public class StudySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studySessionID;

    @ManyToOne
    @JoinColumn(name="UserID", nullable = false)
    private User userID;

    @ManyToOne
    @JoinColumn(name="topicID", nullable = false)
    private Topic topicID;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Column(name = "more_study_required", nullable = false)
    private Boolean moreStudyRequired;

    @Column(name = "date", nullable = false)
    private Date date;

    public Long getStudySessions()
    {
        return studySessionID;
    }

    public User getUserID()
    {
        return userID;
    }

    public Topic getTopicID()
    {
        return topicID;
    }

    public Boolean getCompleted()
    {
        return completed;
    }

    public Boolean getMoreStudyRequired()
    {
        return moreStudyRequired;
    }

    public Date getDate()
    {
        return date;
    }
}
