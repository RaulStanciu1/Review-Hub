package com.reviewhub.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private String content;
    private int line;
    private CommentType type;
}
