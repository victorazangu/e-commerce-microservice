package com.shemi.ecommerce.customer.mapper;

import com.shemi.ecommerce.customer.document.Customer;
import com.shemi.ecommerce.customer.record.CustomerRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest body) {
        if (body == null) {
            return null;
        }
        return  Customer.builder()
                .id(body.id())
                .firstName(body.firstName())
                .lastName(body.lastName())
                .email(body.email())
                .address(body.address())
                .build();
    }
}
