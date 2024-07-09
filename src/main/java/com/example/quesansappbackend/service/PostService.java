package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Post;
import com.example.quesansappbackend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

   private PostRepository postRepository;

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }else
            return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(Post newPost) {
        return postRepository.save(newPost);
    }
}
