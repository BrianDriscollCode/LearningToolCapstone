package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "Uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "LastLogin")
    private Date lastLogin;

    @Column(name = "OnboardingFinished")
    private Boolean onboardingFinished;

    @Column(name = "initialDataGenerated")
    private Boolean initialDataGenerated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Subject> subjects = new HashSet<>();

    public Long getUserID() {
        return userID;
    }

    public UUID getUUID() { return uuid; }

    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
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

}
