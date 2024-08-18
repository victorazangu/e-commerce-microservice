package com.shemi.ecommerce.customer.service.impl;

import com.shemi.ecommerce.customer.document.Customer;
import com.shemi.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.shemi.ecommerce.customer.mapper.CustomerMapper;
import com.shemi.ecommerce.customer.record.CustomerRequest;
import com.shemi.ecommerce.customer.repository.CustomerRepository;
import com.shemi.ecommerce.customer.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer createCustomer(CustomerRequest body) {
        Customer save = customerRepository.save(customerMapper.toCustomer(body));
        System.out.println();
        System.out.println("the created customer " + save);
        System.out.println();
        return save;
    }

    @Override
    public Customer updateCustomer(CustomerRequest body, String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                String.format("Customer with id %s not found", id)
                        )
                );

        Customer newCustomer = customerMapper.toCustomer(body);
        Customer customer1 = mergeCustomer(customer, newCustomer);
        customerRepository.save(customer1);
        return customer1;
    }

    @Override
    public Optional<List<Customer>> getCustomers() {
        return Optional.of(customerRepository.findAll());
    }

    @Override
    public Optional<Customer> getCustomer(String id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(
                        String.format("Customer with id %s not found", id)
                )
        ));
    }

    @Override
    public String deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(
                        String.format("Customer with id %s not found", id)
                )
        );
        customerRepository.deleteById(id);
        return String.format("Customer with id %s deleted", id);
    }

    private Customer mergeCustomer(Customer customer, Customer newCustomer) {
        if (StringUtils.isNotBlank(newCustomer.getFirstName())) {
            customer.setFirstName(newCustomer.getFirstName());
        }
        if (StringUtils.isNotBlank(newCustomer.getLastName())) {
            customer.setLastName(newCustomer.getLastName());
        }
        if (StringUtils.isNotBlank(newCustomer.getEmail())) {
            customer.setEmail(newCustomer.getEmail());
        }
        if (newCustomer.getAddress() != null) {
            customer.setAddress(newCustomer.getAddress());
        }
        return customer;
    }
}
