package com.example.quesansappbackend.repository;

import com.example.quesansappbackend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
