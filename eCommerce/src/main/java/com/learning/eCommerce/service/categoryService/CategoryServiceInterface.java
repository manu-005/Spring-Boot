package com.learning.eCommerce.service.categoryService;

import com.learning.eCommerce.dto.category.CategoryRequestDto;
import com.learning.eCommerce.dto.category.CategoryResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryServiceInterface {
    CategoryResponseDto createCategory(@Valid CategoryRequestDto dto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto updateCategory(Long id, @Valid CategoryRequestDto dto);

    void deleteCategory(Long id);
}
