package com.reviewhub.entities;

import lombok.Data;

@Data
public class Comment {
    private String content;
    private int line;
    private CommentType type;
}
