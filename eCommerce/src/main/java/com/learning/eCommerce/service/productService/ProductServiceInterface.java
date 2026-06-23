package com.learning.eCommerce.service.productService;

import com.learning.eCommerce.dto.productsDTO.ProductRequestDto;
import com.learning.eCommerce.dto.productsDTO.ProductResponseDto;

import java.util.List;

public interface ProductServiceInterface {

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id,
                                     ProductRequestDto requestDto);

    void deleteProduct(Long id);
}