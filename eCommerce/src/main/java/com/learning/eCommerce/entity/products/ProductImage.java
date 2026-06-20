package com.learning.eCommerce.entity.products;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_images")
public class ProductImage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String imageUrl;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private ProductEntity product;
}
