package com.Comment.Microservice.Payload;

import lombok.Data;

@Data

public class Post {

    private String id;
    private String title;
    private String content;
    private String description;
}
