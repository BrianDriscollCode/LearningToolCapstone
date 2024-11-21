package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectID;

    @ManyToOne
    @JoinColumn(name="UserID", nullable = false)
    private User user;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "NumberOfTopics")
    private Integer NumberOfTopics;

    public Long getSubjectID()
    {
        return subjectID;
    }

    public User getUserID()
    {
        return user;
    }

    public String getName()
    {
        return name;
    }

    public Integer getNumberOfTopics()
    {
        return NumberOfTopics;
    }


}
