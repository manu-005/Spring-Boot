package com.learning.eCommerce.exception.productException;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) { super(message); }
}