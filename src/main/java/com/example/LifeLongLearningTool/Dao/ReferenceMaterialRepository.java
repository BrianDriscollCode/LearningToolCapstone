package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.ReferenceMaterial;
import com.example.LifeLongLearningTool.Entity.StudySession;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceMaterialRepository extends JpaRepository<ReferenceMaterial, Long> {
    List<ReferenceMaterial> findByTopic(Topic topic);
}