package com.learning.eCommerce.exception.productException;

public class SkuAlreadyExistsException
        extends RuntimeException {

    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}