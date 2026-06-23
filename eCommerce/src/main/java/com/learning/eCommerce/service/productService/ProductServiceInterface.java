package com.learning.eCommerce.service.products;

import com.learning.eCommerce.dto.productsDTO.ProductRequestDto;
import com.learning.eCommerce.dto.productsDTO.ProductResponseDto;

import java.util.List;

public interface ProductServiceInterface {

    ProductResponseDto createProduct(ProductRequestDto dto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    void deleteProduct(Long id);
}