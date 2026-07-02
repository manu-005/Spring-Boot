package com.learning.eCommerce.entity.cartEntity;

import com.learning.eCommerce.entity.cartItemEntity.CartItemEntity;
import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cartEntity")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer;

    @Column(name = "total_price")
    private Double totalPrice = 0.0;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

}
