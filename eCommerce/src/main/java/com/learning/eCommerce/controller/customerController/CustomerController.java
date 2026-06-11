package com.learning.eCommerce.controller.customerController;

import com.learning.eCommerce.dto.customerDTO.CustomerRegistrationRequestDto;
import com.learning.eCommerce.service.customerService.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(
            @Valid @RequestBody CustomerRegistrationRequestDto requestDto) {

        customerService.registerCustomer(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Customer registered successfully");
    }


}
