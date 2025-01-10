package com.example.LifeLongLearningTool.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Reference_Material")
public class ReferenceMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="topicID", nullable = false)
    private Topic topic;

    @Column(name = "name")
    private String name;

    @Column(name = "Location")
    private String location;

    public Long getReferenceID() { return referenceID; }
    public void setReferenceID(Long referenceID) { this.referenceID = referenceID; }

    public Topic getTopic() { return topic; }
    public void setTopic(Topic topic) { this.topic = topic; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
