package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsBySku(String sku);
}