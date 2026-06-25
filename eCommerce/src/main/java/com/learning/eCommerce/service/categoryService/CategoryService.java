package com.learning.eCommerce.service.categoryService;

import com.learning.eCommerce.dto.category.CategoryRequestDto;
import com.learning.eCommerce.dto.category.CategoryResponseDto;
import com.learning.eCommerce.entity.category.Category;
import com.learning.eCommerce.exception.categoryException.CategoryAlreadyExistsException;
import com.learning.eCommerce.exception.categoryException.CategoryInUseException;
import com.learning.eCommerce.exception.productException.CategoryNotFoundException;
import com.learning.eCommerce.mapper.CategoryMapper;
import com.learning.eCommerce.repository.CategoryRepository;
import com.learning.eCommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

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

    public CategoryResponseDto getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        return categoryMapper.toResponseDto(category);
    }

    public CategoryResponseDto updateCategory(
            Long id,
            CategoryRequestDto dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updatedCategory =
                categoryRepository.save(category);

        return categoryMapper.toResponseDto(updatedCategory);
    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id));

        if (productRepository.existsByCategoryId(id)) {
            throw new CategoryInUseException(
                    "Cannot delete category because products are associated with it");
        }
        categoryRepository.delete(category);
    }
}