package com.learning.eCommerce.service.customerService;

import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import com.learning.eCommerce.dto.loginDto.AuthResponseDTO;
import com.learning.eCommerce.dto.loginDto.LogInRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerServiceInterface {
    CustomerResponseDTO registerCustomer(@Valid CustomerDTO requestDto);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);

    CustomerResponseDTO updateCustomer(Long id, CustomerDTO dto);

    void deleteCustomer(Long id);

    AuthResponseDTO customerLogin(@Valid LogInRequestDTO dto);
}
