package com.learning.eCommerce.dto.productsDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponseDto {

    private Long id;

    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String brand;

    private String status;

    private Long categoryId;

    private String categoryName;

    private List<String> imageUrls;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}