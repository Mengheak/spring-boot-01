package com.example.springboot_01.mapper;


import com.example.springboot_01.dto.stock.StockDto;
import com.example.springboot_01.dto.stock.StockResponseDto;
import com.example.springboot_01.entity.Product;
import com.example.springboot_01.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {
    public Stock toEntity (StockDto dto, Product product){
        Stock entity = new Stock();
        entity.setProduct(product);
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    public StockResponseDto toDto(Stock entity){
        StockResponseDto dto = new StockResponseDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setProductId(entity.getProduct().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public List<StockResponseDto> toDtoList (List<Stock> entities){
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
