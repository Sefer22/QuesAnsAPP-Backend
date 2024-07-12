package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.repository.LikeRepository;
import com.example.quesansappbackend.request.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
    }

    public Like getOneLikeById(Long likeId) {
    }

    public void deleteOneLikeById(Long likeId) {
    }
}
