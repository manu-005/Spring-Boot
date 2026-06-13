package com.learning.eCommerce.controller.customerController;

import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import com.learning.eCommerce.service.customerService.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //  CREATE
    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO dto) {
        CustomerResponseDTO created = customerService.registerCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //  GET ALL
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

//    // ✅ GET BY ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
//        Customer customer = customerService.getCustomerById(id);
//        return ResponseEntity.ok(customer);
//    }


//    // ✅ UPDATE
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> updateCustomer(
//            @PathVariable Long id,
//            @RequestBody CustomerDTO dto) {
//        Customer updated = customerService.updateCustomer(id, dto);
//        return ResponseEntity.ok(updated);
//    }

//    // ✅ DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//        return ResponseEntity.ok("Customer deleted successfully");
//    }

}
