package com.example.LifeLongLearningTool.Dao;

import com.example.LifeLongLearningTool.Entity.Employee;
import com.example.LifeLongLearningTool.Entity.Question;
import com.example.LifeLongLearningTool.Entity.Topic;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findByUuid(UUID uuid);
}
