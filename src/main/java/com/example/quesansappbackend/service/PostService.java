package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Post;
import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.repository.PostRepository;
import com.example.quesansappbackend.request.PostCreateRequest;
import com.example.quesansappbackend.request.PostUpdateRequest;
import com.example.quesansappbackend.response.PostResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

   private PostRepository postRepository;
   private UserService userService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;
    }

    @Transactional
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
             list = postRepository.findByUserId(userId.get());
        }
            list = postRepository.findAll();
        return list.stream().map(post -> new PostResponse(post)).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if(user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId,PostUpdateRequest postUpdateRequest) {
    Optional<Post> post = postRepository.findById(postId);
    if(post.isPresent()) {
        Post toUpdate = post.get();
        toUpdate.setText(postUpdateRequest.getText());
        toUpdate.setTitle(postUpdateRequest.getTitle());
         postRepository.save(toUpdate);
         return toUpdate;
    }
    return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
