package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.SubjectRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.Subject;
import com.example.LifeLongLearningTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public void SubjectService(UserRepository userRepository, SubjectRepository subjectRepository)
    {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects()
    {
        return subjectRepository.findAll();
    }

    public void createSubject(UUID uuid, String name)
    {
        Subject subject = new Subject();
        System.out.println("UUID" + uuid);
        User user = userRepository.findByUuid(uuid);
        if (user == null) {
            throw new IllegalArgumentException("User with UUID " + uuid + " not found.");
        }
        System.out.println("CRASH1");
        subject.setUser(user);
        System.out.println("USER: " + user);
        subject.setName(name);
        subjectRepository.save(subject);
    }
    //User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User cannot be found with ID"));
}
