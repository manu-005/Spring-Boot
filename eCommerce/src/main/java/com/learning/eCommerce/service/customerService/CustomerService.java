package com.learning.eCommerce.service.customerService;

import com.learning.eCommerce.mapper.CustomerMapper;
import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import com.learning.eCommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerResponseDTO responseDTO;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO registerCustomer(CustomerDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (customerRepository.existsByEmail (dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (customerRepository.existsByMobileNumber(dto.getMobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        CustomerEntity entity = new CustomerEntity();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setMobileNumber(dto.getMobileNumber());

        // Encrypt Password
        String encryptedPassword =
                passwordEncoder.encode(dto.getPassword());

        entity.setPassword(encryptedPassword);

       CustomerEntity created =  customerRepository.save(entity);

        return customerMapper.entityToRespDTO(created);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        return customerMapper.entityToRespDTOList(allCustomers);  // empty list if no customers
    }
}
