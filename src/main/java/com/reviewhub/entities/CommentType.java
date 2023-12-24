package com.reviewhub.entities;

public enum CommentType {
    Comment,
    Todo,
    Fixme,
    Alternative;

    public static CommentType parseCommentType(String commentType) {
        switch (commentType) {
            case "Comment":
                return Comment;
            case "Todo":
                return Todo;
            case "Fixme":
                return Fixme;
            case "Alternative":
                return Alternative;
            default:
                return null;
        }
    }
}
