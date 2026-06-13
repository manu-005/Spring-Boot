package com.learning.eCommerce.dto.customerDTO;

import lombok.Data;

@Data
public class CustomerResponseDTO {

        private Long customerId;
        private String firstName;
        private String email;
        private String mobileNumber;
//        private String address;
    }

