package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.products.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long>{

}
