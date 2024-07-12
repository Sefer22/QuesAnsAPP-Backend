package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.request.LikeCreateRequest;
import com.example.quesansappbackend.response.LikeResponse;
import com.example.quesansappbackend.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                          @RequestParam Optional<Long> postId) {
      return likeService.getAllLikesWithParam(userId,postId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createOneLike(likeCreateRequest);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }
    @DeleteMapping("/{id}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }
}
