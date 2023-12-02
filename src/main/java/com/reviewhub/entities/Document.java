package com.reviewhub.entities;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private String content;
    private String type;
    private ArrayList<Comment> comments;

    public Document(List<String> content, String type) {
        this.content = String.join("\n", content);
        this.type = type;
        this.comments = new ArrayList<Comment>();
    }

    public String getContent() {
        return this.content;
    }
}
