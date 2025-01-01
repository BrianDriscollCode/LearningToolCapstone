package com.example.LifeLongLearningTool.Dto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//src: https://www.baeldung.com/spring-boot-chatgpt-api-openai

public class ChatResponse {

    private List<Choice> choices;

    public ChatResponse()
    {
        this.choices = new ArrayList<>();
    }

    public List<Choice> getChoices() { return choices; }
    public void setChoices(List<Choice> choices) { this.choices = choices; }

    public static class Choice
    {
        private int index;
        private ChatRequest.Message message;

        public int getIndex() { return index; }
        public void setIndex(int index) { this.index = index; }

        public ChatRequest.Message getMessage() { return message; }
        public void setMessage(ChatRequest.Message message) { this.message = message;}
    }
}
