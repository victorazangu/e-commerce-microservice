package com.shemi.ecommerce.customer.repository;

import com.shemi.ecommerce.customer.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
