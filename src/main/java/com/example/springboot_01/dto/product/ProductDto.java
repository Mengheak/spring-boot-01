package com.example.springboot_01.dto.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private Double price;
    private String description;
}
