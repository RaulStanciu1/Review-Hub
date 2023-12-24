package com.reviewhub.services;

import com.reviewhub.dto.openai.ChatRequest;
import com.reviewhub.dto.openai.ChatResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@Getter
@Setter
@NoArgsConstructor
@Service
public class ChatService {
    @Qualifier("openaiRestTemplate")
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;

    public String chat(String prompt) {
        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if(response == null || response.getChoices() == null || response.getChoices().isEmpty()){
            return "No Response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
    public String rewrite(String prompt) {
        String finalPrompt = prompt+ "\n Please rewrite the following piece of code, adding comments, documentation, and any clarifications that are necessary. You can rewrite the function, but do not alter the return values or types. Assume any libraries or packages are predefined. Only respond in the programing language that the input is written in\n";
        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if(response == null || response.getChoices() == null || response.getChoices().isEmpty()){
            return "No Response";
        }
        System.out.println(response.getChoices().get(0).getMessage().getContent());
        return response.getChoices().get(0).getMessage().getContent();
    }
}
