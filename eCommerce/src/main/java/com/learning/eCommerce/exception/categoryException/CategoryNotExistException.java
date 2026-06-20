package com.learning.eCommerce.exception.categoryException;

public class CategoryNotExistException extends  RuntimeException{

    public CategoryNotExistException(String message) {
        super(message);
    }
}
