package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.SubjectDTO;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Service.SubjectService;
import com.example.LifeLongLearningTool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects()
    {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubject(@RequestBody SubjectDTO subjectDTO)
    {
        System.out.println("CREATE SUBJECT RAN");

        subjectService.createSubject(subjectDTO.getUserUuid(), subjectDTO.getName());
        return ResponseEntity.ok("Created subject successfully");
    }
}
