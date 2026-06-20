package com.learning.eCommerce.service.categoryService;

import com.learning.eCommerce.dto.category.CategoryRequestDto;
import com.learning.eCommerce.dto.category.CategoryResponseDto;
import com.learning.eCommerce.entity.category.Category;
import com.learning.eCommerce.exception.category.CategoryAlreadyExistsException;
import com.learning.eCommerce.mapper.CategoryMapper;
import com.learning.eCommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDto createCategory(CategoryRequestDto dto) {

        if (categoryRepository.existsByName(dto.getName())) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }

        Category category = categoryMapper.toEntity(dto);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDto(savedCategory);
    }

    public List<CategoryResponseDto> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseDto)
                .toList();
    }
}