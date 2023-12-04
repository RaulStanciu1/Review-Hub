package com.reviewhub.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Document {
    private String content;
    private DocumentType type;
    private ArrayList<Comment> comments;

    public Document(List<String> content, String type) {
        this.content = String.join("\n", content);
        this.type = DocumentType.parseDocType(type);
        this.comments = new ArrayList<Comment>();
    }

}
