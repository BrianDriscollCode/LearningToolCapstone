package com.example.LifeLongLearningTool.Entity.BaseClasses;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class Person {

    private String name;
    private String email;
    private UUID uuid;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UUID getUUID() { return uuid; }
    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
    }

}
