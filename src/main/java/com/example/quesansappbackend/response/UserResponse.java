package com.example.quesansappbackend.response;

import com.example.quesansappbackend.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    Long id;
    int avatarId;
    String username;

    public UserResponse (User entity) {
        this.id = entity.getId();
        this.avatarId=entity.getAvatar();
        this.username=entity.getUserName();
    }
}
