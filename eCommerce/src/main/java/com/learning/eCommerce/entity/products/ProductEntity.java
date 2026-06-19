package com.learning.eCommerce.entity.products;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
