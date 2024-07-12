package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.repository.LikeRepository;
import com.example.quesansappbackend.request.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
      List<Like> list;
      if(userId.isPresent() && postId.isPresent()) {
          list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
      } else if (userId.isPresent()) {
          list = likeRepository.findByUserId(userId);
      } else if (postId.isPresent()) {
          list = likeRepository.findByPostId(postId);
      }else
          list = likeRepository.findAll();
      return list.stream().map(like -> newLikeResponse(like)).collect(Collectors.toList());

    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
