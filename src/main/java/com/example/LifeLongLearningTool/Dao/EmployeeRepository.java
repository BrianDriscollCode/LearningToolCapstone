package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Employee;
import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
