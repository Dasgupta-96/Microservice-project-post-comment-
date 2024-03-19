package com.Post.Microservice.Payload;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {

    private String id;
    private String title;
    private String content;
    private String description;
    private List<Comment> comments;
}
