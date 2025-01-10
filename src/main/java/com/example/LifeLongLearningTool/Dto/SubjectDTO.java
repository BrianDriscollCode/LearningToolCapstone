package com.example.LifeLongLearningTool.Dto;

import com.example.LifeLongLearningTool.Entity.User;
import com.example.LifeLongLearningTool.Enum.Competency;

import java.util.UUID;

public class SubjectDTO {
    private String name;
    private String uuid;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public UUID getUserUuid() { return UUID.fromString(uuid); }
}
