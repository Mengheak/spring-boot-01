package com.example.springboot_01.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockModel {
    private Long productId;
    private Long quantity;
}
