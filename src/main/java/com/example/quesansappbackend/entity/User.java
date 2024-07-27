package com.example.quesansappbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String password;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer avatar = 0;
}
