package com.learning.eCommerce.repository;

import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByMobileNumber(String mobileNumber) {
        return false;
    }

    @Override
    public void save(CustomerEntity entity) {

        System.out.println(entity);

    }
}
