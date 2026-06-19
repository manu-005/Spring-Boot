package com.learning.eCommerce.mapper;

import com.learning.eCommerce.entity.products.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductEntity toEntity(ProductDto dto, CategoryEntity category) ;

    public ProductResponseDto toResponseDto(ProductEntity entity);

}
