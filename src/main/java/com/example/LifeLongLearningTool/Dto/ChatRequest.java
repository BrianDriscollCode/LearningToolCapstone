package com.example.LifeLongLearningTool.Dto;

import org.aspectj.bridge.Message;

import java.util.ArrayList;
import java.util.List;

//src: https://www.baeldung.com/spring-boot-chatgpt-api-openai

public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;
    private double top_p;

    public ChatRequest(String model, String prompt, int n, double temperature, double top_p)
    {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));

        this.n = n;
        this.top_p = top_p;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    public double getTemperature() { return temperature; }
    public void setTemperature() { this.temperature = temperature; }

    public double getTop_p() { return top_p; }
    public void setTop_p(double top_p) { this.top_p = top_p; }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content)
        {
            this.role = role;
            this.content = content;
        }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
