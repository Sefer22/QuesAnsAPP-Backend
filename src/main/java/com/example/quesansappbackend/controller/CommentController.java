package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.Comment;
import com.example.quesansappbackend.request.CommentCreateRequest;
import com.example.quesansappbackend.request.CommentUpdateRequest;
import com.example.quesansappbackend.response.CommentResponse;
import com.example.quesansappbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId,postId);
    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createOneComment(commentCreateRequest);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        commentService.deleteOneCommentById(commentId);
    }

}
