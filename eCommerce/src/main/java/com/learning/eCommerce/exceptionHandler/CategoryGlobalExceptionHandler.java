package com.learning.eCommerce.exceptionHandler;

import com.learning.eCommerce.exception.categoryException.CategoryAlreadyExistsException;
import com.learning.eCommerce.exception.categoryException.CategoryNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryGlobalExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> handleCategoryAlreadyExists(
            CategoryAlreadyExistsException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<String> handleCategoryNotFound(
            CategoryNotExistException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


}