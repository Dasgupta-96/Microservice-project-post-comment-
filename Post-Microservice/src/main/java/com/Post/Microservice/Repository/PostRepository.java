package com.Post.Microservice.Repository;

import com.Post.Microservice.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
}
