package com.example.springboot_01.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    private Double price;
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;


    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
