package com.example.quesansappbackend.response;

import com.example.quesansappbackend.entity.Like;
import com.example.quesansappbackend.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<Like> postLikes;

    public PostResponse(Post entity) {
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.userName=entity.getUser().getUserName();
        this.title=entity.getTitle();
        this.text=entity.getText();
    }
}
