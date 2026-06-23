package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findBySku(String sku);

    boolean existsBySku(String sku);
}