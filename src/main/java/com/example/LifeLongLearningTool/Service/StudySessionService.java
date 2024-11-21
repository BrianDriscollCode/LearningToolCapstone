package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.StudySessionRepository;
import com.example.LifeLongLearningTool.Dao.UserRepository;
import com.example.LifeLongLearningTool.Entity.StudySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySessionService {

    @Autowired
    private StudySessionRepository studySessionRepository;

    public List<StudySession> getAllStudySessions()
    {
        return studySessionRepository.findAll();
    }
}
