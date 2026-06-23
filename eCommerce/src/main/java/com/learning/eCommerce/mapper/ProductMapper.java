package com.learning.eCommerce.mapper;

import com.learning.eCommerce.dto.productsDTO.ProductRequestDto;
import com.learning.eCommerce.dto.productsDTO.ProductResponseDto;
import com.learning.eCommerce.entity.products.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(ProductRequestDto dto);

    ProductResponseDto toResponseDto(ProductEntity entity);
}