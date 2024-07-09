package com.example.quesansappbackend.repository;

import com.example.quesansappbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
