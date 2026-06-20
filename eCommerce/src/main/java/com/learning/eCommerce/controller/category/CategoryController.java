package com.learning.eCommerce.controller.category;

import com.learning.eCommerce.dto.category.CategoryRequestDto;
import com.learning.eCommerce.dto.category.CategoryResponseDto;
import com.learning.eCommerce.service.categoryService.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // adding category
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(
            @Valid @RequestBody CategoryRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(dto));
    }

    //get all category
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }

    // get categories by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(id)
        );
    }

    //update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDto dto) {

        return ResponseEntity.ok(
                categoryService.updateCategory(id, dto)
        );
    }

    //delete categories
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully");
    }


}