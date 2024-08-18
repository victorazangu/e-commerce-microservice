package com.shemi.ecommerce.customer.service;

import com.shemi.ecommerce.customer.document.Customer;
import com.shemi.ecommerce.customer.record.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService  {
    public Customer createCustomer(CustomerRequest customer);
    public Customer updateCustomer(CustomerRequest customer,String id);
    public Optional<List<Customer>> getCustomers();
    public Optional<Customer> getCustomer(String id);
    public String deleteCustomer(String id);
}
