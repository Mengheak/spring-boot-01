package com.example.springboot_01.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "email") specify name of a column. if not it'll take the name of props as col name
    private String email;
    private String name;
    private Integer age;
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;
}
