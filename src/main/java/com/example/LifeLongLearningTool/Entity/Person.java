package com.example.LifeLongLearningTool.Entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    private String name;
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}