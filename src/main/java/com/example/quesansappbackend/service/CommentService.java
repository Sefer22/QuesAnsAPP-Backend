package com.example.quesansappbackend.service;

import com.example.quesansappbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
