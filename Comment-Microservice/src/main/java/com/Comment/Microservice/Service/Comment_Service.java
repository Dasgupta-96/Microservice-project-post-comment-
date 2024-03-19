package com.Comment.Microservice.Service;

import com.Comment.Microservice.Config.RestTemplateConfig;
import com.Comment.Microservice.Entity.Comment;
import com.Comment.Microservice.Payload.Post;
import com.Comment.Microservice.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Comment_Service {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplate;
    public Comment saveComment(Comment comment) {

        Post post = restTemplate.getRestTemplate().getForObject("http://localhost:81/api/posts/" + comment.getPostId(), Post.class);

        if (post != null) {
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment save = commentRepository.save(comment);
            return save;
        }
        else {

            return null;
        }

    }
    public Comment getComments(String commentId){

        Comment comment = commentRepository.findById(commentId).get();

        return comment;
    }

    public  List<Comment> getCommentByPostId(String postId){

        List<Comment> comment = commentRepository.findByPostId(postId);

        return comment;
    }
}
