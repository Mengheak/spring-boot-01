package com.example.springboot_01.mapper;

import com.example.springboot_01.dto.product.ProductDto;
import com.example.springboot_01.dto.product.ProductResponseDto;
import com.example.springboot_01.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product toEntity(ProductDto dto){
        Product entity = new Product();
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public ProductResponseDto toDto(Product entity){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
    public List<ProductResponseDto> toDtoList (List<Product> entities){
        ProductResponseDto dto = new ProductResponseDto();
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
