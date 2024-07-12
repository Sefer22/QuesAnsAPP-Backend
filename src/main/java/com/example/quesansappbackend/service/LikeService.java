package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.entity.Post;
import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.repository.LikeRepository;
import com.example.quesansappbackend.request.LikeCreateRequest;
import com.example.quesansappbackend.response.LikeResponse;
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

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
      List<Like> list;
      if(userId.isPresent() && postId.isPresent()) {
          list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
      } else if (userId.isPresent()) {
          list = likeRepository.findByUserId(userId);
      } else if (postId.isPresent()) {
          list = likeRepository.findByPostId(postId);
      }else
          list = likeRepository.findAll();
      return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());

    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getOneUserById(likeCreateRequest.getUserId());
        Post post = postService.getOnePostById(likeCreateRequest.getPostId());
        if(user !=null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
