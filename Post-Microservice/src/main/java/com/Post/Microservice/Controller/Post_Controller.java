package com.Post.Microservice.Controller;

import com.Post.Microservice.Entity.Post;
import com.Post.Microservice.Payload.PostDto;
import com.Post.Microservice.Service.Post_Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class Post_Controller {

    @Autowired
    private Post_Service ser;
    @PostMapping
    // http://localhost:8081/api/posts
    public ResponseEntity<?> savePosts(@RequestBody Post post){

        Post data = ser.savePost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    // http://localhost:8081/api/posts/{postId}
    public ResponseEntity<?> getPost(@PathVariable String postId){

        Post post = ser.getPost(postId);

        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/{postId}/particular")
    @CircuitBreaker(name = "commentBreaker", fallbackMethod = "commentFallback")
    // http://localhost:8081/api/posts/{postId}/particular
    public ResponseEntity<?> getPostWithComments(@PathVariable String postId){

        PostDto data = ser.getPostWithComments(postId);

        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    public ResponseEntity<PostDto> commentFallback(String postId, Exception ex) {
        System.out.println("Fallback is executed because service is down : "+ ex.getMessage());

        ex.printStackTrace();

        PostDto dto = new PostDto();
        dto.setId("1234");
        dto.setTitle("Service Down");
        dto.setContent("Service Down");
        dto.setDescription("Service Down");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
