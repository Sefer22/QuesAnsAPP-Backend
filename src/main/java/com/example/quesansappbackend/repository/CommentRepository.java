package com.example.quesansappbackend.repository;

import com.example.quesansappbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

    List<Comment> findByUserId(Optional<Long> userId);

    List<Comment> findByPostId(Optional<Long> postId);

    @Query(value = "select * from comment where post_id in :postIds limit 5",
            nativeQuery = true)
    List<Comment> findUserCommentByPostId(@Param("postIds") List<Long> postIds);
}
