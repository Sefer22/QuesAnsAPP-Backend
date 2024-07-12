package com.example.quesansappbackend.response;

import com.example.quesansappbackend.entity.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
