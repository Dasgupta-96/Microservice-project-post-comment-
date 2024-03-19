package com.Post.Microservice.Payload;

import lombok.Data;

@Data
public class Comment {

    private String commentId;
    private String email;
    private String body;
    private String postId;
}
