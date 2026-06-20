package com.learning.eCommerce.mapper;

import com.learning.eCommerce.dto.category.CategoryRequestDto;
import com.learning.eCommerce.dto.category.CategoryResponseDto;
import com.learning.eCommerce.entity.category.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")

public interface CategoryMapper {

    Category toEntity(CategoryRequestDto dto);

    CategoryResponseDto toResponseDto(Category entity);
}