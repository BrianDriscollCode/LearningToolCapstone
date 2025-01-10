package com.example.LifeLongLearningTool.Entity;

import com.example.LifeLongLearningTool.Entity.BaseClasses.Person;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "OnboardingFinished")
    private Boolean onboardingFinished;

    @Column(name = "initialDataGenerated")
    private Boolean initialDataGenerated;

    @OneToOne
    @JoinColumn(name="EmployeeID", nullable = true)
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Subject> subjects = new HashSet<>();

    public Long getUserID() {
        return userID;
    }

    public Boolean getOnboardingFinished()
    {
        return onboardingFinished;
    }

    public void setOnboardingFinished(boolean onboardingFinished)
    {
        this.onboardingFinished = onboardingFinished;
    }

    public Boolean getInitialDataGenerated() {return initialDataGenerated; }

    public void setInitialDataGenerated(Boolean initialDataGenerated) { this.initialDataGenerated = initialDataGenerated; }

    public Employee getEmployee() { return this.employee; }

}
