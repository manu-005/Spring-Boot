package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}