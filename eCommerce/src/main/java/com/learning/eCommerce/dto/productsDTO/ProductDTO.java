package com.learning.eCommerce.dto.productsDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private Long categoryId;
}

