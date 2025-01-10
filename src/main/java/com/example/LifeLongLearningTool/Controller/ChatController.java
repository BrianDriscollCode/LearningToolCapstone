package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.ChatRequest;
import com.example.LifeLongLearningTool.Dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//src: https://www.baeldung.com/spring-boot-chatgpt-api-openai


@RestController
@RequestMapping("/api/ai")
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        try {
            ChatRequest request = new ChatRequest(model, prompt, 1, 1, 1);

            ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,
                    new HttpEntity<>(request), ChatResponse.class);

            ChatResponse response = responseEntity.getBody();

            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                return "No response";
            }

            List<ChatResponse.Choice> choices = response.getChoices();

            for (ChatResponse.Choice choice: choices)
            {
                System.out.println(choice);
            }

            return response.getChoices().get(0).getMessage().getContent();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error occurred while processing the request.";
        }
    }


    @GetMapping("/test-openai")
    public String testOpenAI() {
        try {
            String testUrl = "https://api.openai.com/v1/models";  // Example endpoint
            ResponseEntity<String> response = restTemplate.exchange(
                    testUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(null),
                    String.class
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


}
