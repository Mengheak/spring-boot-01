package com.example.springboot_01.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductModel {
    private Long id;
    private String productName;
    private Double price;
    private String description;
}
