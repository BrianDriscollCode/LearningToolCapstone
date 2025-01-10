package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Study_Sessions")
public class StudySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_sessionid")
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
    private LocalDate date;

    public Long getStudySessionID() { return studySessionID; }
    public void setStudySessionID(Long studySessionID) { this.studySessionID = studySessionID; }

    public User getUserID() { return userID; }
    public void setUserID(User userID) { this.userID = userID; }

    public Topic getTopicID()
    {
        return topicID;
    }
    public void setTopicID(Topic topicID) { this.topicID = topicID; }

    public Boolean getCompleted()
    {
        return completed;
    }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public Boolean getMoreStudyRequired()
    {
        return moreStudyRequired;
    }
    public void setMoreStudyRequired(Boolean moreStudyRequired) { this.moreStudyRequired = moreStudyRequired; }

    public LocalDate getDate()
    {
        return date;
    }
    public void setDate(LocalDate date) { this.date = date; }
}
