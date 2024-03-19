package com.Comment.Microservice.Controller;

import com.Comment.Microservice.Entity.Comment;
import com.Comment.Microservice.Service.Comment_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private Comment_Service ser;
    @PostMapping
    // http://localhost:8082/api/comments
    public ResponseEntity<?> saveComments(@RequestBody Comment comment){

        Comment data = ser.saveComment(comment);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}/particular")
    // http://localhost:8082/api/comments/{commentId}
    public ResponseEntity<?> getComments(@PathVariable String commentId){

        Comment data = ser.getComments(commentId);

        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    //http://localhost:8082/api/comments/{postId}
    public ResponseEntity<?> getCommentByPostId(@PathVariable String postId){

        ser.getCommentByPostId(postId)

        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
