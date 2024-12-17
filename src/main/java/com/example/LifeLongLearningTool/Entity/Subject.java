package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private Set<Topic> topics = new HashSet<>();

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
