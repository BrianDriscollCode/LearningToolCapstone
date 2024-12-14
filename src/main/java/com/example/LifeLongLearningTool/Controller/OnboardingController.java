package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.OnboardingDTO;
import com.example.LifeLongLearningTool.Service.OnboardingService;
import com.example.LifeLongLearningTool.Service.SubjectService;
import com.example.LifeLongLearningTool.Service.TopicService;
import com.example.LifeLongLearningTool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping("/create")
    public ResponseEntity<String> onboardUser(@RequestBody OnboardingDTO onboardingDTO)
    {
        System.out.println("TEST");
        onboardingService.finishOnboarding(onboardingDTO.getUuid(), onboardingDTO.getSubjectName(), onboardingDTO.getTopicName1(), onboardingDTO.getCompetency1(), onboardingDTO.getTopicName2(), onboardingDTO.getCompetency2());
        return ResponseEntity.ok("Onboarding successful");
    }

}
