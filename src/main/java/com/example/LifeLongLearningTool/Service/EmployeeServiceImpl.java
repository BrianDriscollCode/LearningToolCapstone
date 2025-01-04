package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Entity.Employee;

import java.util.List;
import java.util.UUID;

public class EmployeeServiceImpl extends UserService
{

    @Override
    public void createUser(UUID uuid, String name, String email)
    {
        Employee employee = new Employee();
        employee.setUUID(uuid);
        employee.setName(name);
        employee.setEmail(email);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByID(Long id)
    {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public Employee getEmployeeByUuid(String uuid)
    {
        UUID uuidReturnObject = UUID.fromString(uuid);
        System.out.println(userRepository.findByUuid(uuidReturnObject));
        return employeeRepository.findByUuid(uuidReturnObject);
    }

    public void deleteEmployeeWithUUID(UUID uuid)
    {
        Employee employee = employeeRepository.findByUuid(uuid);
        employeeRepository.delete(employee);;
    }

    public void setEmployeePersonNameByID(Long id, String name) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        employee.setName(name);
        employeeRepository.save(employee);
    }

}
