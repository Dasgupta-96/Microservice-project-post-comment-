package com.Comment.Microservice.Repository;

import com.Comment.Microservice.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {

   List<Comment> findByPostId(String postId);
}
