package com.learning.eCommerce.dto.productsDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponseDto {

    private Long id;

    private String name;

    private String sku;

    private BigDecimal price;

    private Integer quantity;

    private String brand;

    private String status;

    private String categoryName;

    private List<String> imageUrls;
}