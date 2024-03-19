package com.Post.Microservice.Service;

import com.Post.Microservice.Config.RestTemplateConfig;
import com.Post.Microservice.Entity.Post;
import com.Post.Microservice.Payload.PostDto;
import com.Post.Microservice.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class Post_Service {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplateConfig restTemplate;
    public Post savePost(Post post){

        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post save = postRepository.save(post);
return save;
    }

    public Post getPost(String postId){

        Post post = postRepository.findById(postId).get();

        return post;
    }

    public PostDto getPostWithComments(String postId){

        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://localhost:82/api/comments/" + postId, ArrayList.class);

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setComments(comments);

        return dto;

    }
}
