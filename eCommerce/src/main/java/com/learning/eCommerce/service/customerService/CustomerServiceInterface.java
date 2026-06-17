package com.learning.eCommerce.service.customerService;

import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerServiceInterface {
    CustomerResponseDTO registerCustomer(@Valid CustomerDTO requestDto);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);
}
