package com.reviewhub.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Document {
    private String content;
    private String type;
    private ArrayList<Comment> comments;

    public Document(List<String> content, String type) {
        this.content = String.join("\n", content);
        this.type = type;
        this.comments = new ArrayList<>();
    }

    public Document(String content, String type) {
        this.content = content;
        this.type = type;
        this.comments = new ArrayList<>();
    }

    public void addComment(int lineNum, String comment, String commentType) {
        this.comments.add(new Comment(comment, lineNum, CommentType.parseCommentType(commentType)));
    }
}
