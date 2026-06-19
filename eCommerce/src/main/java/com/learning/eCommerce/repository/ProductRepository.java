package com.learning.eCommerce.repository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory_Name(String categoryName);
    List<ProductEntity> findByPriceBetween(Double minPrice, Double maxPrice);
}