package com.shemi.ecommerce.customer.record;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
){
}
