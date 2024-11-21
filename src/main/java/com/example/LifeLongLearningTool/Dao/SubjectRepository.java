package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}