package com.example.quesansappbackend.repository;

import com.example.quesansappbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);

    @Query(value = "select id from post order by create_date desc limit 5 where user_id =:userId",
            nativeQuery = true)
    List<Long> findTopByUserId(@Param("userId") Long userId);
}
