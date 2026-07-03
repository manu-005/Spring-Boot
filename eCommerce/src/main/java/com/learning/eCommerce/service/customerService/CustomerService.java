package com.learning.eCommerce.service.customerService;

import com.learning.eCommerce.dto.loginDto.AuthResponseDTO;
import com.learning.eCommerce.dto.loginDto.LogInRequestDTO;
import com.learning.eCommerce.exception.customerException.CustomerNotFoundException;
import com.learning.eCommerce.exception.customerException.DuplicateEmailException;
import com.learning.eCommerce.exception.customerException.DuplicateMobileNumberException;
import com.learning.eCommerce.exception.customerException.InvalidCredential;
import com.learning.eCommerce.mapper.CustomerMapper;
import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import com.learning.eCommerce.repository.CustomerRepository;
import com.learning.eCommerce.service.jwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceInterface {

    // All final fields — injected via @RequiredArgsConstructor
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;
    private final JwtService jwtService;

    @Override
    public CustomerResponseDTO registerCustomer(CustomerDTO dto) {

        if (dto == null) {
            throw new CustomerNotFoundException("Request cannot be null");
        }

        if (customerRepository.existsByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email already registered");
        }

        if (customerRepository.existsByMobileNumber(dto.getMobileNumber())) {
            throw new DuplicateMobileNumberException("Mobile number already registered");
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

        CustomerEntity created = customerRepository.save(entity);

        return customerMapper.entityToRespDTO(created);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerRepository.findAll();


        System.out.println("Entity before mapping: " + allCustomers);

        List<CustomerResponseDTO> response = customerMapper.entityToRespDTOList(allCustomers);
        System.out.println("DTO after mapping: " + response);

        return response;  // empty list if no customers
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {

        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        return customerMapper.entityToRespDTO(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerDTO dto) {

        // Step 1 — Find customer, throw 404 if not found
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        // Step 2 — Update only the fields that are provided
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setMobileNumber(dto.getMobileNumber());

        // Step 3 — Save updated customer
        CustomerEntity updatedCustomer = customerRepository.save(customer);

        // Step 4 — Convert to response DTO and return
        return customerMapper.entityToRespDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        // Step 1 — Check if customer exists, throw 404 if not found
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        // Step 2 — Delete customer
        customerRepository.delete(customer);
    }

    @Override
    public AuthResponseDTO customerLogin(LogInRequestDTO dto) {

        Optional<CustomerEntity> optionalCustomer = customerRepository.existsByEmail(dto.getEmail());

        if (optionalCustomer.isEmpty()) {
            throw new InvalidCredential("Invalid email or password");
        }

        CustomerEntity customer = optionalCustomer.get();

        // Compare raw password with encrypted password
        if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
            throw new InvalidCredential("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtService.generateToken(customer.getEmail());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setMessage("Login successful");

        return response;
    }

}
