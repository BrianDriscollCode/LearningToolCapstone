package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "LastLogin")
    private Date lastLogin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Subject> subjects = new HashSet<>();


    public Long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public Date getLastLogin() {
        return lastLogin;
    }


}
