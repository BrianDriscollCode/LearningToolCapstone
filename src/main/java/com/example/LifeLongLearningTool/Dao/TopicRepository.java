package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}