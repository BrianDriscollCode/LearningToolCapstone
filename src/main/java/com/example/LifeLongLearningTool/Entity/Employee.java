package com.example.LifeLongLearningTool.Entity;

import com.example.LifeLongLearningTool.Entity.BaseClasses.Person;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Employees")
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @Column(name="is_admin")
    private Boolean is_admin;

    public Long getEmployeeID() { return employeeID; }

    public Boolean getIs_Admin() { return is_admin; }
}