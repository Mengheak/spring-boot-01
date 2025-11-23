package com.example.springboot_01.dto.stock;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockResponseDto {
    @JsonProperty("stock_id")
    private Long id;

    @JsonProperty("product_id")
    private Long productId;
    private Long quantity;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
