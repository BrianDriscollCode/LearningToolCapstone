package com.example.LifeLongLearningTool.Dto;

import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Enum.Competency;

import java.util.UUID;

public class TopicDTO {
    private String name;
    private String competency;
    private String uuid;
    private Long subjectID;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompetency() { return competency; }
    public void setCompetency(String competency) { this.competency = competency; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public UUID getUserUuid() { return UUID.fromString(uuid); }

    public Long getSubjectID() { return subjectID; }
    public void setSubjectID(Long subjectID) { this.subjectID = subjectID; }
}
