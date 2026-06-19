package com.learning.eCommerce.dto.productsDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String categoryName;
}