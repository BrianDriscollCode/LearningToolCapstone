package com.example.LifeLongLearningTool.Entity;

import com.example.LifeLongLearningTool.Enum.Competency;
import com.example.LifeLongLearningTool.Enum.LearningStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "competency", nullable = false)
    private Competency competency = Competency.BEGINNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "learning_status", nullable = false)
    private LearningStatus learningStatus = LearningStatus.ACTIVE;

    public Long getTopicID()
    {
        return topicID;
    }

    public Subject getSubjectID()
    {
        return subjectID;
    }
    public void setSubjectID(Subject subjectID) { this.subjectID = subjectID; }

    public String getName()
    {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public Competency getCompetency()
    {
        return competency;
    }
    public void setCompetency(Competency competency) { this.competency = competency; }

    public LearningStatus getLearningStatus() { return learningStatus; }
    public void setLearningStatus(LearningStatus learningStatus) { this.learningStatus = learningStatus; }

}
