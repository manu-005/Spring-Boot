package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
}
