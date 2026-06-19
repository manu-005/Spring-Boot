package com.learning.eCommerce.exception.productException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) { super(message); }
}
