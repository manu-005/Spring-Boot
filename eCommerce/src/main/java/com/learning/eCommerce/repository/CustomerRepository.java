package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public interface CustomerRepository {
    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    void save(CustomerEntity entity);
}
