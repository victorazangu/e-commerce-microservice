package com.shemi.ecommerce.customer.controller;

import com.shemi.ecommerce.customer.document.Customer;
import com.shemi.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.shemi.ecommerce.customer.record.CustomerRequest;
import com.shemi.ecommerce.customer.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody @Valid CustomerRequest body
    ) {
        return ResponseEntity.ok(customerService.createCustomer(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody @Valid CustomerRequest body,
            @PathVariable String id
    ) {
        Customer customer = customerService.updateCustomer(body, id);
        return ResponseEntity.ok(customer);
    }


    @GetMapping
    public ResponseEntity<Map<String,Optional< List<Customer>>>> getAllCustomers() {
        Optional<List<Customer>> customers = Optional.ofNullable(customerService.getCustomers().orElseThrow(() ->
                new CustomerNotFoundException("Not found")));
        Map<String, Optional<List<Customer>>> customersList = new HashMap<>();
        customersList.put("data", customers);
        return ResponseEntity.ok(customersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        String data = customerService.deleteCustomer(id);
        return ResponseEntity.ok(data);
    }
}
