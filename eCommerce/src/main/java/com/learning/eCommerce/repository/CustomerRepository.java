package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
}
