package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.ReferenceMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceMaterialService {

    @Autowired
    private ReferenceMaterialRepository referenceMaterialRepository;
}
