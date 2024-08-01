package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.Post;
import com.example.quesansappbackend.request.PostCreateRequest;
import com.example.quesansappbackend.request.PostUpdateRequest;
import com.example.quesansappbackend.response.PostResponse;
import com.example.quesansappbackend.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

   private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public PostResponse getOnePost(@PathVariable Long postId) {
        return postService.getOnePostByIdWithLikes(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {

        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updateOnePostById(postId,postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
