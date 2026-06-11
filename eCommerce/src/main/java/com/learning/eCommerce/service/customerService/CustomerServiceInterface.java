package com.learning.eCommerce.service.customerService;

import com.learning.eCommerce.dto.customerDTO.CustomerRegistrationRequestDto;
import jakarta.validation.Valid;

public interface CustomerServiceInterface {
    String registerCustomer(@Valid CustomerRegistrationRequestDto requestDto);
}
