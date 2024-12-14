package com.example.LifeLongLearningTool.Dto;

public class OnboardingDTO {
    private String uuid;

    private String subjectName;

    private String topicName1;
    private String competency1;

    private String topicName2;
    private String competency2;

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getTopicName1() { return topicName1; }
    public void setTopicName1(String topicName) { this.topicName1 = topicName; }

    public String getCompetency1() { return competency1; }
    public void setCompetency1(String competency1) { this.competency1 = competency1; }

    public String getTopicName2() { return topicName2; }
    public void setTopicName2(String topicName2) { this.topicName2 = topicName2; }

    public String getCompetency2() { return competency2; }
    public void setCompetency2(String competency2) { this.competency2 = competency2; }

}
