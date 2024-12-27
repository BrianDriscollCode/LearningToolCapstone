package com.example.LifeLongLearningTool.Dto;

import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Enum.LearningStatus;
import com.example.LifeLongLearningTool.Utils.KeyValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearningMapDataDTO {
    private Subject subject;
    private List<KeyValuePair<Topic, Integer>> numberOfStudySessions;

    public LearningMapDataDTO(Subject subject, List<KeyValuePair<Topic, Integer>> numberOfStudySessions)
    {
        this.subject = subject;
        this.numberOfStudySessions = numberOfStudySessions;
    }

    public Subject getSubject()
    {
        return this.subject;
    }

    public void setSubject(Subject subject)
    {
        this.subject = subject;
    }


    public List<KeyValuePair<Topic, Integer>> getNumberOfStudySessions() { return this.numberOfStudySessions; }

    public void setNumberOfStudySessions(List<KeyValuePair<Topic, Integer>> numberOfStudySessions) { this.numberOfStudySessions = numberOfStudySessions; }

}
