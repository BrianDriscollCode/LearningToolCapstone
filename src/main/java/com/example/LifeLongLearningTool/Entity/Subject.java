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

    public Long getSubjectID()
    {
        return subjectID;
    }

    public User getUser()
    {
        return user;
    }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
