package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Service.ReferenceMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/referenceMaterial")
public class ReferenceMaterialController {

    @Autowired
    ReferenceMaterialService referenceMaterialService;

}
