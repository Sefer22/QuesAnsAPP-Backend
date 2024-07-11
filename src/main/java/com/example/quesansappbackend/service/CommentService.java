package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Comment;
import com.example.quesansappbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId,postId);
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(Optional.of(userId.get()));
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(Optional.of(postId.get()));
        }else  return commentRepository.findAll();

    }
}
