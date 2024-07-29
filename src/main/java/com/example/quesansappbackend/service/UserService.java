package com.example.quesansappbackend.service;

import com.example.quesansappbackend.entity.Comment;
import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.repository.CommentRepository;
import com.example.quesansappbackend.repository.LikeRepository;
import com.example.quesansappbackend.repository.PostRepository;
import com.example.quesansappbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return  userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty()) {
            return null;
        }
       List<Comment> comments = commentRepository.findUserCommentByPostId(postIds);
       List<Like> likes =  likeRepository.findUserLikesByPostId(postIds);
       List<Object> result = new ArrayList<>();
       result.addAll(comments);
       result.addAll(likes);
       return result;
    }
}
