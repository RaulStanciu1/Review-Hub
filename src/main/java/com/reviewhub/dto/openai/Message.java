package com.reviewhub.dto.openai;


import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;

    public Message(String user, String prompt) {
        this.role = user;
        this.content = prompt;
    }
}
