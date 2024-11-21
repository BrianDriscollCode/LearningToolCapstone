package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicID;

    @ManyToOne
    @JoinColumn(name="SubjectID", nullable = false)
    private Subject subjectID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "competency", nullable = false)
    private String competency;

    public Long getTopicID()
    {
        return topicID;
    }

    public Subject getSubjectID()
    {
        return subjectID;
    }

    public String getName()
    {
        return name;
    }

    public String getCompetency()
    {
        return competency;
    }

}
