package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Comment;
import com.example.quesansappbackend.entity.Post;
import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.repository.CommentRepository;
import com.example.quesansappbackend.request.CommentCreateRequest;
import com.example.quesansappbackend.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostService postService;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
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

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUserById(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());
        if(user !=null && post !=null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentCreateRequest.getText());
            return commentRepository.save(commentToSave);
        }else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment=commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
         commentRepository.deleteById(commentId);
    }
}
